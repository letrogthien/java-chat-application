/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Data;

/**
 *
 * @author agin0
 */
@Data
public class ConnectionHandler {

    private static final String DATABASE_URL = "jdbc:mysql://152.42.248.95/winapp?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Abcd1234";
    private Connection connection;

    public ConnectionHandler() {
        this.connection = null;
    }

    public void connectDb( ) {
        try {
            if (this.connection != null) {
                System.out.println("connect already");
            }
            if (this.connection == null) {
                this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                System.out.println("connect success");
            }  
            
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }

    public boolean closeConnection( ) {
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection=null;
            }
            
            System.out.println("close success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
