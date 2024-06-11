/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.models;

/**
 *
 * @author agin0
 */

import java.util.Date;

import lombok.Data;

@Data
public class Message {
    private Integer senderId;
    private Integer recipientId;
    private Date timestamp;
    private String content;
    private Messagetype messageType;
    private boolean readReceipt;
    private String chatId;
    private String status;
}
