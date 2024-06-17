/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.restapi;

/**
 *
 * @author agin0
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.winform.models.Message;
import java.net.URI;


import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class ChatClient {

    public List<Message> getMessage(Integer u1, Integer u2) {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://152.42.225.60/v1/getmessage?u1=%d&u2=%d", u1, u2);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response body: " + response.body());
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Message>>(){}.getType();
            List<Message> messages = gson.fromJson(response.body(), listType);
            return messages;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList(); 
    }
}
