/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.services;

import com.winform.config.db.ConnectionHandler;
import com.winform.config.session.SessionManager;
import com.winform.models.User;
import com.winform.models.UserStatus;
import static com.winform.utills.Utills.encodeFileToBase64Binary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
 import java.io.File;
import java.io.IOException;
@Data
public class UserService {

    private ConnectionHandler connectionHandler;

    public UserService() {
        this.connectionHandler = new ConnectionHandler();
    }

    public User getCurrentUser() {
        SessionManager session = SessionManager.getInstance(null);
        if(session != null){
        Integer userId = session.getUserId();
        if (userId != null) {
            return getUserById(userId);
        }}
        return null;
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

                user.setUserStatus(result.getString(8));
                user.setAvatar(result.getString("avatar"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return users;
    }

    public User getUserById(Integer id) {
        String sqlString = "SELECT * FROM users WHERE id=?";
        User user = new User();
        try {
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            pre.setInt(1, id);
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

    public String getStatusUser(Integer id) {
        String sqlString = "SELECT status FROM users WHERE id=?";
        String rt="";
        try {
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                // Đọc dữ liệu chỉ khi có ít nhất một hàng trong kết quả
                rt = rs.getString("status");
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return rt;

    }

    public List<User> findUSer(String k) {
        String sqlString = "SELECT * FROM users WHERE username LIKE ? OR fullname LIKE ? OR email LIKE ? ";
        List<User> users = new ArrayList<User>();
        try {
            connectionHandler.connectDb();
            PreparedStatement pre = connectionHandler.getConnection().prepareStatement(sqlString);
            String key = "%" + k + "%";
            pre.setString(1, key);
            pre.setString(2, key);
            pre.setString(3, key);

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

                user.setUserStatus(result.getString(8));
                user.setAvatar(result.getString("avatar"));
                users.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionHandler.closeConnection();
        }
        return users;
    }
  private String hashPass(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
     
   public boolean updateUser(String username, User updatedUser) {
    PreparedStatement preUpdate = null;
    int rowsUpdated = 0;
    try {
        connectionHandler.connectDb();

        String sqlUpdate = "UPDATE users SET fullname=?, email=?, phone=?, nickname=?, password=? WHERE username=?";
        preUpdate = connectionHandler.getConnection().prepareStatement(sqlUpdate);
        
        preUpdate.setString(1, updatedUser.getFullName());
        preUpdate.setString(2, updatedUser.getEmail());
        preUpdate.setString(3, updatedUser.getPhone());
        preUpdate.setString(4, updatedUser.getNickName());
        // Mã hóa mật khẩu trước khi thiết lập giá trị cho PreparedStatement.
        String hashedPassword = hashPass(updatedUser.getPassword());
        preUpdate.setString(5, hashedPassword);
        preUpdate.setString(6, username);
        
        rowsUpdated = preUpdate.executeUpdate();
    } catch(SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preUpdate != null) preUpdate.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connectionHandler.closeConnection();
    }
    
    return rowsUpdated > 0;
}
   public boolean updateAvatarByUsernameUsingBase64(String username, File avatarFile) {
    int rowsUpdated = 0;
    PreparedStatement preUpdate = null;
    try {
        // Mã hóa file avatar thành chuỗi Base64
        String avatarBase64 = encodeFileToBase64Binary(avatarFile);

        connectionHandler.connectDb();
        String sqlUpdate = "UPDATE users SET avatar=? WHERE username=?";
        preUpdate = connectionHandler.getConnection().prepareStatement(sqlUpdate);
        
        // Set chuỗi Base64 của avatar
        preUpdate.setString(1, avatarBase64);
        preUpdate.setString(2, username);
        
        // Thực thi cập nhật
        rowsUpdated = preUpdate.executeUpdate();
    } catch(IOException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (preUpdate != null) preUpdate.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connectionHandler.closeConnection();
    }
    
    return rowsUpdated > 0;
}
   public String getAvatarByUsername(String username) {
    PreparedStatement preGet = null;
    ResultSet rs = null;
    String avatarBase64 = null;
    try {
        connectionHandler.connectDb();
        String sqlGet = "SELECT avatar FROM users WHERE username = ?";
        preGet = connectionHandler.getConnection().prepareStatement(sqlGet);
        
        // Set giá trị cho tham số truy vấn
        preGet.setString(1, username);
        
        // Thực thi truy vấn
        rs = preGet.executeQuery();
        
        // Kiểm tra và lấy chuỗi Base64 của avatar nếu tồn tại
        if (rs.next()) {
            avatarBase64 = rs.getString("avatar");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (preGet != null) preGet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connectionHandler.closeConnection();
    }
    
    return avatarBase64;
}
   
}
