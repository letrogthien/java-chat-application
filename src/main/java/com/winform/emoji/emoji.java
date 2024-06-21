/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.emoji;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class emoji {
    private static emoji instance;

    public static emoji getInstance() {
        if (instance == null) {
            instance = new emoji();
        }
        return instance;
    }

    private emoji() {
    }

    public List<Model_Emoji> getStyle1() {
        List<Model_Emoji> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            URL imgUrl = getClass().getResource("/emoji_icon/" + i + ".png");
            if (imgUrl != null) {
                list.add(new Model_Emoji(i, new ImageIcon(imgUrl)));
            } else {
                // Xử lý trong trường hợp file ảnh không tồn tại
            }
        }
        return list;
    }

    public List<Model_Emoji> getStyle2() {
        List<Model_Emoji> list = new ArrayList<>();
        for (int i = 20; i <= 40; i++) {
            URL imgUrl = getClass().getResource("/emoji_icon/" + i + ".png");
            if (imgUrl != null) {
                list.add(new Model_Emoji(i, new ImageIcon(imgUrl)));
            } else {
                // Xử lý trong trường hợp file ảnh không tồn tại
            }
        }
        return list;
    }

    public Model_Emoji getImoji(int id) {
        return new Model_Emoji(id, new ImageIcon(getClass().getResource("/emoji_icon/" + id + ".png")));
    }
}
