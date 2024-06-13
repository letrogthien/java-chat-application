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
import java.util.Arrays;

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
        loginRegister.getParentLogin1().getRegisterForm1().getJButtonRegister().addActionListener(e -> register());
        
    }    
    
    private void login(){
        String username = loginRegister.getParentLogin1().getLoginForm2().getJTextField1().getText();
        char[] password = loginRegister.getParentLogin1().getLoginForm2().getJPasswordField1().getPassword();
        if (authService.login(username, new String(password))) {
            loginRegister.setVisible(false);
            HomeController homeController = new HomeController(main);
            main.setVisible(true);
        }
        else {
             JOptionPane.showMessageDialog(loginRegister,
                                      "Đăng nhập không thành công. Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.",
                                      "Lỗi Đăng Nhập",
                                      JOptionPane.ERROR_MESSAGE);
        }
    }
private void register(){
    // Lấy thông tin người dùng từ form đăng ký
    String username = loginRegister.getParentLogin1().getRegisterForm1().getJTextField1().getText();
    String email = loginRegister.getParentLogin1().getRegisterForm1().getJTextField2().getText();
    String phone = loginRegister.getParentLogin1().getRegisterForm1().getJTextField3().getText();
    char[] password = loginRegister.getParentLogin1().getRegisterForm1().getJPasswordField1().getPassword();
    char[] confirmPassword = loginRegister.getParentLogin1().getRegisterForm1().getJPasswordField2().getPassword();

    // Kiểm tra các trường dữ liệu không được phép trống
    if(username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.length == 0 || confirmPassword.length == 0) {
        JOptionPane.showMessageDialog(loginRegister,
                                      "Vui lòng nhập đầy đủ thông tin.",
                                      "Thông Báo",
                                      JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Kiểm tra mật khẩu và mật khẩu xác nhận có khớp nhau không
    if (!Arrays.equals(password, confirmPassword)) {
        JOptionPane.showMessageDialog(loginRegister,
                                      "Mật khẩu nhập lại không khớp.",
                                      "Lỗi Đăng Ký",
                                      JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Tạo đối tượng User
    User newUser = new User();
    newUser.setUserName(username);
    newUser.setEmail(email);
    newUser.setPhone(phone);
    newUser.setPassword(new String(password));  // Chuyển mảng char thành String

    // Đăng ký người dùng mới
    if (authService.register(newUser)) {  // Sử dụng đối tượng User đã tạo
        JOptionPane.showMessageDialog(loginRegister,
                                      "Đăng ký thành công!",
                                      "Thông Báo",
                                      JOptionPane.INFORMATION_MESSAGE);
        // Chuyển người dùng về màn hình đăng nhập sau khi đăng ký thành công
        showCard("card2");
    } else {
        JOptionPane.showMessageDialog(loginRegister,
                                      "Đăng ký không thành công. Có thể tên đăng nhập hoặc email đã tồn tại.",
                                      "Lỗi Đăng Ký",
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