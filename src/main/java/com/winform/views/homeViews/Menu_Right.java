package com.winform.views.homeViews;

import com.winform.customComponent.PanelRound;
import com.winform.customComponent.UserItem;
import com.winform.eventListener.ChatEvent;
import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.utills.Utills;
import com.winform.views.Main;
import com.winform.views.UserProfileScreen;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JPanel;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author agin0
 */
@Data
public class Menu_Right extends JPanel {

    private List<User> user;
    private ChatEvent chatEvent;
    private UserService userService;
    

    public void setUserSelectionListener(ChatEvent listener) {
        this.chatEvent = listener;
    }

    /**
     * Creates new form Menu_Right
     */
    public Menu_Right() {
        initComponents();
        init(); // Gọi init() để khởi tạo giao diện

        // Sau đó, thiết lập hình ảnh cho avatarBox1
        ImageIcon settingIcon = new ImageIcon(getClass().getResource("/img/setting_ic.png")); // Đảm bảo đường dẫn đúng với vị trí của file ảnh trong dự án của bạn
        avatarBox2.setImage(settingIcon); // Thiết lập hình ảnh cho avatarBox1 

        // Khởi tạo userService
        this.userService = new UserService();
    }

    public void init() {
        jLayeredList.setLayout(new MigLayout("fillx", "0[]0", "4[]4"));

        // Sét đặt sự kiện cho avatarBox2
        avatarBox2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToUserProfileScreen();
            }
        });
    }

    public List<UserItem> setPeople(List<User> user) {
        
        this.user = user;
        return showPeople();
    }

    private List<UserItem> showPeople() {
        jLayeredList.removeAll();
        List<UserItem> userItems= new ArrayList<UserItem>();
        for (User user : this.user) {
            ImageIcon avatarIcon = null;
            if (user.getAvatar() != null && !user.getAvatar().trim().isEmpty()) {
                avatarIcon = Utills.base64ToImageIcon(user.getAvatar());
            } else {
                avatarIcon = new ImageIcon(getClass().getResource("/img/icons8-user-50.png"));
            }
            
            UserItem userItem = new UserItem(user.getUserName(), avatarIcon);
            userItems.add(userItem);
            userItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (chatEvent != null) {
                        chatEvent.onUserSelected(user);
                    }
                    
                    
                }
                
            });

            jLayeredList.add(userItem, "wrap");
        }
        this.jLayeredList.revalidate();
        this.jLayeredList.repaint();
        return userItems;
    }

    private void navigateToUserProfileScreen() {
        // Lấy thông tin người dùng hiện tại từ UserService
        User currentUser = userService.getCurrentUser();
// Kiểm tra nếu không có người dùng hiện tại thì thông báo và không làm gì hơn
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "User information not available.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
// Khởi tạo và hiển thị UserProfileScreen dưới dạng JDialog với người dùng hiện tại
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Tìm JFrame cha
            UserProfileScreen userProfileScreen = new UserProfileScreen(parentFrame, currentUser);
            userProfileScreen.setVisible(true); // Hiển thị JDialog
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelRound2 = new com.winform.customComponent.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLayeredList = new javax.swing.JLayeredPane();
        panelRound1 = new com.winform.customComponent.PanelRound();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        avatarBox2 = new com.winform.swing.AvatarBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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
            .addGap(0, 291, Short.MAX_VALUE)
        );
        jLayeredListLayout.setVerticalGroup(
            jLayeredListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jLayeredList);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Search");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(avatarBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avatarBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.swing.AvatarBox avatarBox2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private com.winform.customComponent.PanelRound panelRound1;
    private com.winform.customComponent.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
