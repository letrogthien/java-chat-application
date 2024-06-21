package com.winform.views.homeViews;

import com.winform.event.EventChat;
import com.winform.event.PublicEvent;
import java.awt.Dimension;
import lombok.Data;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JSeparator;
/**
 *
 * @author agin0
 */
@Data
public class Chat extends javax.swing.JPanel {
   
    public Chat() {
        initComponents();
        init();
    }

    public void refresh() {
        revalidate();
        repaint();
    }

    private void init() {
        setLayout(new MigLayout("fillx, insets 0", "0[fill]0", "0[fill]0[fill, grow]0[fill]0"));

        add(chat_Title1, "wrap");
        add(chat_Body1, "grow,wrap");
         // Add the separator line between body and bottom
        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(32, 205, 232));
        separator.setForeground(new Color(32, 205, 232));
        separator.setPreferredSize(new java.awt.Dimension(1, 2)); // Set a preferred size for better visibility
        add(separator, "growx, hmin 2, wrap"); // Ensure the separator takes full width and is visible
        add(chat_Bottom1, "h ::50%");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chat_Body1 = new com.winform.views.homeViews.Chat_Body();
        chat_Title1 = new com.winform.views.homeViews.Chat_Title();
        chat_Bottom1 = new com.winform.views.homeViews.Chat_Bottom();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(400, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chat_Bottom1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chat_Title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chat_Body1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chat_Title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat_Body1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chat_Bottom1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.views.homeViews.Chat_Body chat_Body1;
    private com.winform.views.homeViews.Chat_Bottom chat_Bottom1;
    private com.winform.views.homeViews.Chat_Title chat_Title1;
    // End of variables declaration//GEN-END:variables
}
