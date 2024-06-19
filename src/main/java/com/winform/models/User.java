/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class User {

    private Integer id;
    @JsonProperty("username")
    private String userName;
    private String password;
    private String phone;
    private String email;
    @JsonProperty("fullname")
    private String fullName;
    @JsonProperty("nickname")
    private String nickName;
    @JsonProperty("status")
    private String userStatus;
    private String avatar;
}
