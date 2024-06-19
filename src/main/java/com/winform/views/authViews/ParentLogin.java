/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.winform.views.authViews;

import lombok.Data;

/**
 *
 * @author agin0
 */
@Data
public class ParentLogin extends javax.swing.JPanel {

    /**
     * Creates new form ParentLogin
     */
    public ParentLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginForm2 = new com.winform.views.authViews.LoginForm();
        forgotPass1 = new com.winform.views.authViews.ForgotPass();
        registerForm1 = new com.winform.views.authViews.RegisterForm();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());

        loginForm2.setBackground(new java.awt.Color(255, 255, 255));
        add(loginForm2, "card2");
        add(forgotPass1, "card3");
        add(registerForm1, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.winform.views.authViews.ForgotPass forgotPass1;
    private com.winform.views.authViews.LoginForm loginForm2;
    private com.winform.views.authViews.RegisterForm registerForm1;
    // End of variables declaration//GEN-END:variables
}
