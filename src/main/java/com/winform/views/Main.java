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
import com.winform.swing.ComponentResizer;
import com.winform.views.homeViews.Home;
import com.winform.views.homeViews.View_image;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import lombok.Data;

/**
 *
 * @author agin0
 */
@Data
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
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
        view_image.setVisible(false);
        home.setVisible(true);
        initEvent();
    }
    
    private void initEvent() {
        PublicEvent.getInstance().addEventImageView(new EventImageView() {
            @Override
            public void viewImage(Icon image) {
                view_image.viewImage(image);
            }

            @Override
            public void saveImage(Icon image) {
                System.out.println("Save Image next update");
            }

        });
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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
         FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("TextComponent.arc", 15);
        UIManager.put("Button.arc", 15);
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.views.homeViews.Home home;
    private com.winform.views.homeViews.View_image view_image;
    // End of variables declaration//GEN-END:variables
}
