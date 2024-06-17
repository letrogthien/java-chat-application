/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.controllers;

import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.views.homeViews.Menu_Right;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author agin0
 */
@Data

public class MenuRightController {
    private Menu_Right menu_Right;
    private UserService userService;

    public MenuRightController(Menu_Right menu_Right) {
        this.menu_Right = menu_Right;
        this.userService = new UserService();
    }

    
 
    
    public void init(){
        this.menu_Right.getJButton1().addActionListener(e -> searchAction());
    }
    
    private void searchAction(){
        String searchString = this.menu_Right.getJTextField1().getText();
        List<User> users= userService.findUSer(searchString);
        menu_Right.setUser(users);
        
    }
    
    
    
   
    
}
