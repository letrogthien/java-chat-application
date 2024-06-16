/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.winform.views.homeViews;

import com.winform.customComponent.PanelRound;
import com.winform.customComponent.UserItem;
import com.winform.eventListener.ChatEvent;
import com.winform.models.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import lombok.Data;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author agin0
 */
@Data
public class Menu_Right extends JPanel {

    private List<User> user;
    private ChatEvent chatEvent;

    public void setUserSelectionListener(ChatEvent listener) {
        this.chatEvent = listener;
    }

    /**
     * Creates new form Menu_Right
     */
    public Menu_Right() {
        initComponents();
        init();
    }

    public void init() {
        jLayeredList.setLayout(new MigLayout("fillx", "0[]0", "4[]4"));

    }

    public void setPeople(List<User> user) {
        this.user = user;
        showPeople();
    }

    private void showPeople() {
        jLayeredList.removeAll();
        //  test data
        for (User user1 : user) {
            UserItem userItem = new UserItem(user1.getUserName());
            userItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (chatEvent != null) {
                        chatEvent.onUserSelected(user1);
                    }
                }
            });
            jLayeredList.add(userItem, "wrap");

        }
        this.jLayeredList.validate();
        this.jLayeredList.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound2 = new com.winform.customComponent.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLayeredList = new javax.swing.JLayeredPane();
        panelRound1 = new com.winform.customComponent.PanelRound();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setMaximumSize(new java.awt.Dimension(200, 32767));
        setMinimumSize(new java.awt.Dimension(200, 0));

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelRound2.setForeground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopRight(25);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBar(null);

        jLayeredList.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLayeredList.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jLayeredListLayout = new javax.swing.GroupLayout(jLayeredList);
        jLayeredList.setLayout(jLayeredListLayout);
        jLayeredListLayout.setHorizontalGroup(
            jLayeredListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jLayeredListLayout.setVerticalGroup(
            jLayeredListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jLayeredList);

        panelRound1.setBackground(new java.awt.Color(255, 204, 204));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredList;
    private javax.swing.JScrollPane jScrollPane1;
    private com.winform.customComponent.PanelRound panelRound1;
    private com.winform.customComponent.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
