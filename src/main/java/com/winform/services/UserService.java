/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.services;

import com.winform.config.db.ConnectionHandler;
import com.winform.models.User;
import com.winform.models.UserStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author agin0
 */
@Data
public class UserService {

    private ConnectionHandler connectionHandler;

    public UserService() {
        this.connectionHandler = new ConnectionHandler();
    }

    public List<User> getListUser() {
        String sqlString = "SELECT * FROM users";
        List<User> users = new ArrayList<User>();
        try {
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            ResultSet result = pre.executeQuery();

            while (result.next()) {
                User user = new User();
                user.setId(result.getInt(1));
                user.setUserName(result.getString(2));
                user.setPassword(result.getString(3));
                user.setPhone(result.getString(4));
                user.setEmail(result.getString(5));
                user.setFullName(result.getString(6));
                user.setNickName(result.getString(7));
                if (result.getString(8) != null) {
                    user.setUserStatus(UserStatus.valueOf(result.getString(8).toUpperCase()));
                }
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return users;
    }

}
