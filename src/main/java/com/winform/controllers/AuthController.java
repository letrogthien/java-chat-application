/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.controllers;

import com.winform.models.User;
import com.winform.services.AuthService;
import com.winform.views.LoginRegister;
import com.winform.views.Main;
import com.winform.views.authViews.LoginForm;
import com.winform.views.authViews.ParentLogin;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author agin0
 */
public class AuthController {
    private LoginRegister loginRegister;
    private User user;
    private AuthService authService;
    private Main main;

    public AuthController(LoginRegister loginRegister,Main main) {
        this.loginRegister = loginRegister;
        this.authService=new AuthService();
        this.main=main;
        initController();
    }
    
    private void initController() {
        LoginForm loginForm = loginRegister.getParentLogin1().getLoginForm2();
        loginForm.getJButton1().addActionListener(e -> login());
        addButtonActionToCard(loginRegister.getParentLogin1().getLoginForm2().getJButtonGetRegister(), "card4");
        addButtonActionToCard(loginRegister.getParentLogin1().getLoginForm2().getJButtonGetPass(), "card3");
        addButtonActionToCard(loginRegister.getParentLogin1().getRegisterForm1().getJButtonLogin(), "card2");
        addButtonActionToCard(loginRegister.getParentLogin1().getForgotPass1().getJButtonGetLogin(), "card2");
        
    }
    
    
    private void login(){
        String username = loginRegister.getParentLogin1().getLoginForm2().getJTextField1().getText();
        char[] password = loginRegister.getParentLogin1().getLoginForm2().getJPasswordField1().getPassword();
        if (authService.login(username, new String(password))) {
            loginRegister.setVisible(false);
            main.setVisible(true);
        }
        else {
             JOptionPane.showMessageDialog(loginRegister,
                                      "Đăng nhập không thành công. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.",
                                      "Lỗi Đăng Nhập",
                                      JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addButtonActionToCard(JButton button, String cardName) {
        button.addActionListener(e -> {
            showCard(cardName);
        });
    }

    private void showCard(String cardName) {
        CardLayout cardLayout = (CardLayout) loginRegister.getParentLogin1().getLayout();
        cardLayout.show(loginRegister.getParentLogin1(), cardName);
    }
}