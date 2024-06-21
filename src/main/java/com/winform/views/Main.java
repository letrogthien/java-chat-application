/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.winform.views;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import javax.swing.UIManager;
import com.winform.*;
import com.winform.event.EventImageView;
import com.winform.event.PublicEvent;
import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.swing.ComponentResizer;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.*;
import lombok.Data;

@Data
public class Main extends javax.swing.JFrame {
    private UserService userService; // Đối tượng userService để lấy thông tin người dùng
    private UserProfileScreen userProfileScreen; // Chú ý, khai báo UserProfileScreen cần import đúng package
    private User currentUser; // Giả sử bạn có thông tin user hiện tại
    private CardLayout cardLayout;

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/icon/icon.png")).getImage());
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(900, 500));
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));

        cardLayout = (CardLayout) getContentPane().getLayout();

        // Initialize and add components to container
        initUserProfileScreen();
        home.setVisible(true);
      }

    private void initUserProfileScreen() {
        userService = new UserService();
        currentUser = userService.getCurrentUser();
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // 'this' nên là component hiện tại

        if (currentUser != null) {
            userProfileScreen = new UserProfileScreen(parentFrame,currentUser);
            getContentPane().add(userProfileScreen, "UserProfile");
        } else {
            System.out.println("Không thể lấy thông tin người dùng hiện tại.");
            // Handle case where user information is not available, e.g., show login screen
        }
    }

    private void initHomeScreen() {
        home = new com.winform.views.homeViews.Home();
        getContentPane().add(home, "Home");
    }

   

    public void switchToCard(String cardName) {
        cardLayout.show(getContentPane(), cardName);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        view_image = new com.winform.views.homeViews.View_image();
        home = new com.winform.views.homeViews.Home();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1800, 1200));
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(view_image, "card3");
        getContentPane().add(home, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.views.homeViews.Home home;
    private com.winform.views.homeViews.View_image view_image;
    // End of variables declaration//GEN-END:variables
}
