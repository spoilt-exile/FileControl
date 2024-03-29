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
 * Entry add and edit dialog;
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public class EntryDialog extends javax.swing.JDialog {
    
    /**
     * Null reference indicator;
     */
    private Boolean isNew = false;
    
    /**
     * Processed control entry;
     */
    private ControlEntry processedEntry;
    
    /**
     * Entry which should be return to quene;
     */
    private ControlEntry returnedEntry;

    /**
     * Creates new form EntryDialog
     */
    public EntryDialog(java.awt.Frame parent, boolean modal, ControlEntry givenEntry) {
        super(parent, modal);
        initComponents();
        if (givenEntry == null) {
            this.saveBut.setText("Добавить");
            this.isNew = true;
        } else {
            this.processedEntry = givenEntry;
            this.entryNameField.setText(this.processedEntry.EntryName);
            this.pathField.setText(this.processedEntry.Path);
            this.maskField.setText(this.processedEntry.Mask);
            this.ignoreMaskField.setText(this.processedEntry.IgnoreMask);
            this.caseSwitch.setSelected(this.processedEntry.CaseSensetive);
            this.errorCondSwitch.setSelected(this.processedEntry.ErrorCondition);
            this.folderIgnore.setSelected(this.processedEntry.IgnoreFolder);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entryNameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pathField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        maskField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ignoreMaskField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        errorCondSwitch = new javax.swing.JCheckBox();
        caseSwitch = new javax.swing.JCheckBox();
        cancelBut = new javax.swing.JButton();
        saveBut = new javax.swing.JButton();
        folderIgnore = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Настройка записи");

        jLabel1.setText("Имя записи");

        jLabel2.setText("Путь поиска");

        jLabel3.setText("Маска");

        jLabel4.setText("Игнорировать маску");

        errorCondSwitch.setSelected(true);
        errorCondSwitch.setText("Сигнализировать о присутствии");

        caseSwitch.setText("Учитывать регистр");

        cancelBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        cancelBut.setText("Отмена");
        cancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButActionPerformed(evt);
            }
        });

        saveBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        saveBut.setText("Сохранить");
        saveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButActionPerformed(evt);
            }
        });

        folderIgnore.setSelected(true);
        folderIgnore.setText("Игнорировать папки ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(entryNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathField)
                                .addGap(65, 65, 65))
                            .addComponent(maskField)
                            .addComponent(ignoreMaskField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveBut)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBut))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(folderIgnore)
                            .addComponent(errorCondSwitch)
                            .addComponent(caseSwitch))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entryNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ignoreMaskField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(errorCondSwitch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(caseSwitch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(folderIgnore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBut)
                    .addComponent(saveBut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButActionPerformed

    private void saveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButActionPerformed
        this.returnedEntry = new ControlEntry(this.entryNameField.getText(),
            this.pathField.getText(), this.maskField.getText(), 
            this.ignoreMaskField.getText(), this.caseSwitch.isSelected(),
            this.errorCondSwitch.isSelected(), this.folderIgnore.isSelected());
        if (this.isNew) {
            ControlQuene.addToQuene(returnedEntry);
        } else {
            ControlQuene.replaceWithinQuene(processedEntry, returnedEntry);
        }
        FileControl.MainWindow.buildControlTable();
        this.dispose();
    }//GEN-LAST:event_saveButActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EntryDialog dialog = new EntryDialog(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBut;
    private javax.swing.JCheckBox caseSwitch;
    private javax.swing.JTextField entryNameField;
    private javax.swing.JCheckBox errorCondSwitch;
    private javax.swing.JCheckBox folderIgnore;
    private javax.swing.JTextField ignoreMaskField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField maskField;
    private javax.swing.JTextField pathField;
    private javax.swing.JButton saveBut;
    // End of variables declaration//GEN-END:variables
}
