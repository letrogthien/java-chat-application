/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.services;

import com.winform.config.db.ConnectionHandler;
import com.winform.config.session.SessionManager;
import com.winform.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author agin0
 */
//@Query Pattern
//public void methodName(User user) {
//        String sqlString = "query";
//        
//        try {
//            connectionHandler.connectDb();
//            //do somgthing
//            
//            
//        } catch (SQLException e) {
//            e.printStackTrace(); 
//        }
//        finally {
//            connectionHandler.closeConnection();
//        }
//    }
@Data
public class AuthService {

    private ConnectionHandler connectionHandler;

    public AuthService() {
        this.connectionHandler = new ConnectionHandler();
    }

    public String hashPass(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public User getUserByUsername(String username) {
        String sqlString = "SELECT * FROM users WHERE username=?";
        User user = new User();
        try {
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (!rs.next()) {
                System.out.println("user khong ton tai");
                return null;
            }
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setUserName(rs.getString("username"));
            user.setFullName(rs.getString("fullname"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return user;

    }

    public boolean login(String username, String password) {
        User user;
        user = getUserByUsername(username);
        if (user == null) {
            return false;
        }
        if (!checkPassword(password, user.getPassword())) {
            System.out.println("sai pass");
            return false;
        }
        System.out.println(SessionManager.getInstance(user.getId()));
        return true;
    }

    public boolean register(User user) {
        String sqlString = "INSERT INTO users (username, password, phone, email, fullname) VALUES (?, ?, ?, ?, ?)";
        if (getUserByUsername(user.getUserName())!= null) {
            System.out.println("user already");
            return false;
        }
        try {
            
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            pre.setString(1, user.getUserName());
            pre.setString(2, hashPass(user.getPassword()));
            pre.setString(3, user.getPhone());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getFullName());

            int rowsInserted = pre.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return true;
    }



}
