package com.winform.views.homeViews;

import com.winform.models.User;
import java.awt.Color;
import lombok.Data;

@Data
public class Chat_Title extends javax.swing.JPanel {

    public Chat_Title() {
        initComponents();
    }

    public void refresh() {
        revalidate();
        repaint();

    }

    public void setUserName(String userName) {
        lbName.setText(userName);
    }

    public void statusActive() {
        lbStatus.setText("Online");
        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
    }

    public void setStatusText(String text) {
        lbStatus.setText(text);
        if (text.equalsIgnoreCase("online")) {

            lbStatus.setForeground(new java.awt.Color(40, 147, 59));
        } else {
            lbStatus.setForeground(new Color(160, 160, 160));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 51, 51));
        layer.add(lbName);

        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
        layer.add(lbStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(519, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
