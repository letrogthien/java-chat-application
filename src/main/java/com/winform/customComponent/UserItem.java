/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.winform.customComponent;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author agin0
 */
public class UserItem extends javax.swing.JPanel {

    /**
     * Creates new form UserItem
     */
   public UserItem(String name, ImageIcon avatarIcon) {
    initComponents();
    addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent me) {
            setBackground(new Color(230, 230, 230));
        }

        @Override
        public void mouseExited(MouseEvent me) {
            setBackground(new Color(250, 250, 250));
        }
    });
    jLabelName.setText(name);
    imageAvatar1.setImage(avatarIcon); // Giả sử phương thức này tồn tại và hoạt động
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        imageAvatar1 = new com.winform.customComponent.ImageAvatar();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabelName.setText("name");

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-50.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelName, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.customComponent.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabelName;
    // End of variables declaration//GEN-END:variables
}
