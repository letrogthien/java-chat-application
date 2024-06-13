package com.winform.views.homeViews;

import java.awt.Color;
import javax.swing.Icon;

public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {

    public Chat_Left_With_Profile() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    public void setImageProfile(Icon image) {
        IaImage.setImage(image);
    }

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }

    }

    public void setImage(Icon... image) {
        txt.setImage(false, image);
    }
    
    public void setImage(String... image) {
        txt.setImage(false, image);
    }
    
    public void setFile(String fileName, String fileSize) {
        txt.setFile(fileName, fileSize);
    }


    public void setTime() {
        txt.setTime("10:30 PM");    //  Testing
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new com.winform.swing.ImageAvatar();
        txt = new com.winform.views.homeViews.Chat_Item();
        IaImage = new com.winform.swing.ImageAvatar();

        IaImage.setBorderSize(0);
        IaImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/testing/dog.jpg"))); // NOI18N
        IaImage.setPreferredSize(new java.awt.Dimension(31, 31));

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(IaImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IaImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.swing.ImageAvatar IaImage;
    private com.winform.swing.ImageAvatar imageAvatar1;
    private com.winform.views.homeViews.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
