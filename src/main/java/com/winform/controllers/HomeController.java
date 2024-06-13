/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.controllers;

import com.winform.eventListener.ChatEvent;
import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.views.Main;
import com.winform.views.homeViews.Chat;
import com.winform.views.homeViews.Home;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author agin0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeController {

    private Main main;
    private UserService userService;

    public HomeController(Main main) {
        this.main = main;
        this.userService = new UserService();
        initController();
    }

    private void initController() {

        updateUserList();
      // main.getHome().getMenu_Right1().setUserSelectionListener(user -> updateChatView(user));
    }

 /* private void updateChatView(User user) {
        main.getHome().getChat1().getChat_Body1().removeAll();
        // Cập nhật tiêu đề chat với tên và trạng thái của người dùng được chọn
        main.getHome().getChat1().getChat_Body1().addItemRight(user.getUserName());
        main.getHome().getChat1().getChat_Body1().validate();
        main.getHome().getChat1().getChat_Body1().repaint();

        // Cập nhật nội dung chat...
        // Bạn có thể cần thêm phương thức ở chat body để hiển thị tin nhắn từ/người dùng được chọn
    }*/

    public void updateUserList() {
        List<User> users = userService.getListUser();
        main.getHome().getMenu_Right1().setPeople(users);
    }
}
