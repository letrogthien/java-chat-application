/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.customComponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author agin0
 */
public class ImageRound extends JLabel{

    private Image image;
    
    public ImageRound(Image image, int w, int h) {
        this.image = image;
        // Thiết lập kích thước mặc định dành cho label, giả sử là 100x100 pixels
        Dimension size = new Dimension(w, h);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Chuyển đổi Graphics thành Graphics2D để có thêm lựa chọn về việc hiệu chỉnh đồ họa
            Graphics2D g2d = (Graphics2D) g;
            // Cắt hình ảnh thành hình tròn
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape circleShape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            // Tạo một BufferedImage để vẽ hình ảnh lên đó
            BufferedImage circleBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleBuffer.createGraphics();
            g2.setClip(circleShape);
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
            // Vẽ hình ảnh cuối cùng vào Graphics2D của JLabel
            g2d.drawImage(circleBuffer, 0, 0, this);
        }
    }
    
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
}
