/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.services;

import com.winform.config.db.ConnectionHandler;
import com.winform.config.session.SessionManager;
import com.winform.models.User;
import com.winform.models.UserStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;
import java.util.List;

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
public boolean updatePassword(String username, String email, String newPassword) {
    String sqlCheckUser = "SELECT email, username FROM users WHERE username=?";
    
    try {
        connectionHandler.connectDb();
        PreparedStatement preCheck = connectionHandler.getConnection().prepareStatement(sqlCheckUser);
        preCheck.setString(1, username);
        ResultSet rs = preCheck.executeQuery();
        
        if (!rs.next()) {
            System.out.println("User does not exist.");
            return false; // Người dùng không tồn tại
        } else if (!rs.getString("email").equals(email)) {
            System.out.println("The email does not belong to the given user.");
            return false; // Email không phải của người dùng
        } else {
            // Khi người dùng tồn tại và email đúng
            String sqlUpdatePass = "UPDATE users SET password=? WHERE username=?";
            PreparedStatement preUpdate = connectionHandler.getConnection().prepareStatement(sqlUpdatePass);
            preUpdate.setString(1, hashPass(newPassword));
            preUpdate.setString(2, username);
            int rowsUpdated = preUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully for user: " + username);
                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        connectionHandler.closeConnection();
    }
    return false;
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
            user.setAvatar(rs.getString("avatar"));
            user.setUserStatus(rs.getString(8));
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
