/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.winform.views.homeViews;

/**
 *
 * @author download
 */
public class Chat_File extends javax.swing.JPanel {

    public Chat_File() {
        initComponents();
        setOpaque(false);
    }

    public void setFile(String fileName, String size) {
        lbFileName.setText(fileName);
        lbFileSize.setText(size);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progress2 = new com.winform.swing.Progress();
        jPanel1 = new javax.swing.JPanel();
        lbFileName = new javax.swing.JLabel();
        lbFileSize = new javax.swing.JLabel();

        progress2.setProgressType(com.winform.swing.Progress.ProgressType.FILE);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        lbFileName.setText("my file ");
        jPanel1.add(lbFileName);

        lbFileSize.setForeground(new java.awt.Color(0, 51, 153));
        lbFileSize.setText("5Mb");
        jPanel1.add(lbFileSize);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progress2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbFileName;
    private javax.swing.JLabel lbFileSize;
    private com.winform.swing.Progress progress2;
    // End of variables declaration//GEN-END:variables
}
