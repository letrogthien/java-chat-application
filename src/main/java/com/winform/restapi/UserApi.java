/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winform.models.User;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.Data;

/**
 *
 * @author agin0
 */
@Data
public class UserApi {

    public User getUserById(Integer id) {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/v1/user?id=%d", id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}
