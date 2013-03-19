/**
 * This file is part of FileControl application (check README).
 * Copyright (C) 2013  Stanislav Nepochatov
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
**/

package filecontrol;

/**
 * Quene of control tasks;
 * @author Stanislav Nepochatov
 */
public final class ControlQuene {
    
    /*
     * Quene file name identeficator;
     */
    private static String queneFilename = "Quene.index";
    
    /**
     * Collection of control entries;
     */
    private static java.util.ArrayList<ControlEntry> Quene = new java.util.ArrayList();
    
    /**
     * Interruption flag enumeration
     */
    public static enum InterruptedFlag {
        /**
         * Refresh state of all entries
         */
        REFRESH,
        
        /**
         * Stop checking
         */
        STOP_CHECK,
        
        /**
         * Renew checking
         */
        RENEW_CHECK,
        
        /**
         * Change timer value
         */
        CHANGE_TIMER
    }
    
    /**
     * Current interruption flag;
     */
    public static InterruptedFlag CurrFlag;
    
    /**
     * Current scan thread;
     */
    private static ControlTask CurrTask;
    
    /**
     * Init quene;
     */
    public static void init() {
        java.io.File queneIndex = new java.io.File(FileControl.configPath + "/" + queneFilename);
        if (queneIndex.exists()) {
            try {
                java.io.BufferedReader queneReader = new java.io.BufferedReader(new java.io.FileReader(queneIndex));
                while (queneReader.ready()) {
                    Quene.add(new ControlEntry(queneReader.readLine()));
                }
                queneReader.close();
            } catch (java.io.IOException ex) {
                FileControl.log(0, "Ошибка чтения файла списка записей!");
            } catch (NullPointerException ex) {
                FileControl.log(0, "Файл списка записей поврежден!");
            }
        } else {
            try {
                queneIndex.createNewFile();
            } catch (java.io.IOException ex) {
                FileControl.log(0, "Невозможно создать файл списка записей!");
            }
        }
        CurrTask = new ControlTask(FileControl.MainWindow.getTimerValue());
        CurrTask.start();
    }
    
    /**
     * Reinitialization of the control task;
     */
    private static void reinit() {
        FileControl.log(2, "процес перевірки аварійно перезавантажено!");
        CurrTask = new ControlTask(FileControl.MainWindow.getTimerValue());
        CurrTask.start();
    }
    
    /**
     * Update quene index file;
     */
    public static void updateQueneIndex() {
        Thread indexUpdater = new Thread() {
            @Override
            public void run() {
                StringBuffer newIndex = new StringBuffer();
                java.util.ListIterator<ControlEntry> controlIter = Quene.listIterator();
                while (controlIter.hasNext()) {
                    newIndex.append(controlIter.next().toCsv() + "\r\n");
                }
                try {
                    java.io.File queneIndex = new java.io.File(FileControl.configPath + "/" + queneFilename);
                    queneIndex.delete();
                    java.io.FileWriter queneWriter = new java.io.FileWriter(queneIndex);
                    queneWriter.write(newIndex.toString());
                    queneWriter.close();
                    FileControl.log(3, "Файл списка записей успешно обновлен!");
                } catch (java.io.IOException ex) {
                    FileControl.log(1, "Невозможно сохранить файл списка записей!");
                }
            }
        };
        indexUpdater.start();
    }
    
    /**
     * Separate thread for scaning;
     */
    private static class ControlTask extends Thread {
        
        /**
         * Run trigger;
         */
        private Boolean RUN = true;
        
        /**
         * Check trigger;
         */
        private Boolean CHECK = true;
        
        /**
         * Timer local variable;
         */
        private Integer TIMER_INT = 1;
        
        /**
         * Default constructor;
         * @param givenTimerValue 
         */
        public ControlTask(Integer givenTimerValue) {
            TIMER_INT = givenTimerValue;
        }
        
        @Override
        public void run() {
            while (RUN) {
                try {
                    mainLoop();
                    try {
                        Thread.sleep(TIMER_INT * 60 * 1000);
                    } catch (InterruptedException ex) {
                        switch (CurrFlag) {
                            case REFRESH:
                                break;
                            case STOP_CHECK:
                                this.CHECK = false;
                                break;
                            case RENEW_CHECK:
                                this.CHECK = true;
                                break;
                            case CHANGE_TIMER:
                                this.TIMER_INT = FileControl.MainWindow.getTimerValue();
                                break;
                        }
                    }
                } catch (Exception ex) {
                    FileControl.log(1, "процес перевірки отримав помилку " + ex.getClass().getName());
                    FileControl.log(2, "Зміст помилки:\n" + ex.toString());
                    //reinit();
                }
            }
        }
        
        /**
         * Scan init method;
         */
        private void mainLoop() {
            if (CHECK) {
                FileControl.notifyFlag = false;
                if (Quene.isEmpty()) {
                    FileControl.log(2, "Очередь проверки пуста!");
                    return;
                }
                java.util.ListIterator<ControlEntry> entryiter = Quene.listIterator();
                while (entryiter.hasNext()) {
                    ControlEntry currEntry = entryiter.next();
                    currEntry.check();
                }
                FileControl.MainWindow.userNotify();
                updateControlCells();
            } else {
                FileControl.log(2, "Проверка отключена!");
            }
        }
    }
    
    /**
     * Refresh scaning;
     */
    public static void refreshScan() {
        CurrFlag = InterruptedFlag.REFRESH;
        CurrTask.interrupt();
    }
    
    /**
     * Change timer value in thread;
     */
    public static void setScanTimer() {
        if (CurrTask != null) {
            CurrFlag = InterruptedFlag.CHANGE_TIMER;
            CurrTask.interrupt();
        }
    }
    
    /**
     * Stop scanning;
     */
    public static void stopScan() {
        CurrFlag = InterruptedFlag.STOP_CHECK;
        CurrTask.interrupt();
    }
    
    /**
     * Renew scan;
     */
    public static void renewScan() {
        CurrFlag = InterruptedFlag.RENEW_CHECK;
        CurrTask.interrupt();
    }
    
    /**
     * Add new control entry to quene;
     * @param newEntry new enyry to file scan;
     */
    public static void addToQuene(ControlEntry newEntry) {
        Quene.add(newEntry);
        updateQueneIndex();
        refreshScan();
    }
    
    /**
     * Get control entry by index;
     * @param index index of entry;
     * @return extracted control entry;
     */
    public static ControlEntry getEntry(Integer index) {
        return Quene.get(index);
    }
    
    /**
     * Replace given entry with new one;
     * @param oldEntry old entry (should be in quene or exception will be thrown);
     * @param newEntry new entry;
     */
    public static void replaceWithinQuene(ControlEntry oldEntry, ControlEntry newEntry) {
        Quene.set(Quene.indexOf(oldEntry), newEntry);
        updateQueneIndex();
        refreshScan();
    }
    
    /**
     * Remove control entry from quene;
     * @param index entry's index in table;
     */
    public static void removeFromQuene(Integer index) {
        Quene.remove(Quene.get(index));
        updateQueneIndex();
    }
    
    /**
     * Fill control table with current data;
     * @param givenModel model to fill;
     */
    public static void fillControlTable(javax.swing.table.DefaultTableModel givenModel) {
        java.util.ListIterator<ControlEntry> qiter = Quene.listIterator();
        while (qiter.hasNext()) {
            ControlEntry currEntry = qiter.next();
            givenModel.addRow(new Object[] {currEntry.EntryName, currEntry.Path, currEntry.Mask, currEntry.StrStatus});
        }
    }
    
    /**
     * Update control cells in table;
     */
    public static void updateControlCells() {
        for (Integer cindex = 0; cindex < FileControl.MainWindow.controlTable.getRowCount(); cindex++) {
            ControlEntry curr_entry = ControlQuene.getEntry(cindex);
            FileControl.MainWindow.controlTable.setValueAt(curr_entry.StrStatus, cindex, 3);
            FileControl.MainWindow.controlTable.repaint();
        }
    }
}
