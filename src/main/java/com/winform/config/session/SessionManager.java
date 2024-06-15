/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.config.session;

/**
 *
 * @author agin0
 */
import java.util.Date;
import lombok.Data;

/**
 *
 * @author agin0
 */

import lombok.Data;
import java.util.Date;

@Data
public class SessionManager {
    private static SessionManager instance;
    private Integer userID;
    private Date expiration;

    // Private constructor to prevent instantiation
    private SessionManager(Integer userID) {
        this.userID = userID;
        this.expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hour expiration
    }

    // Public method to provide access to the instance
    public static synchronized SessionManager getInstance(Integer userID) {
        if (instance == null && userID != null) {
            instance = new SessionManager(userID);
        }
        return instance;
    }
    
    public Integer getUserId(){
        return this.userID;
    }

    public boolean isExpiration() {
        return expiration.before(new Date());
    }

    public void clearSession() {
        this.userID = null;
        this.expiration = null;
        instance = null; // Clear the instance
    }

    public void extendSession(long additionalTimeMillis) {
        if (this.expiration != null) {
            this.expiration = new Date(this.expiration.getTime() + additionalTimeMillis);
        }
    }
}