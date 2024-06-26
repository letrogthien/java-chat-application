/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.swing;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.JPanel;


public class AvatarBorderComponent extends JPanel {
    private AvatarBox avatarBox;
    private Color borderColor = Color.BLACK; // Mặc định màu viền là đen
    private int borderThickness = 2;
    private Icon image;
    
    public AvatarBorderComponent(AvatarBox avatarBox) {
        this.avatarBox = avatarBox;
        setLayout(new BorderLayout());
        add(avatarBox, BorderLayout.CENTER);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        Graphics2D g2 = (Graphics2D) grphcs;
        int diameter = Math.min(getWidth(), getHeight());

        if (image != null) {
            Image img = toImage(image);
            Rectangle size = getAutoSize(image);

            BufferedImage circleBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2dTemp = circleBuffer.createGraphics();
            g2dTemp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2dTemp.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
            g2dTemp.drawImage(img, 0, 0, diameter, diameter, null);
            g2dTemp.dispose();

            int x = getWidth() / 2 - (diameter / 2);
            int y = getHeight() / 2 - (diameter / 2);

            g2.drawImage(circleBuffer, x, y, this);
        } else {
            // Nếu không có hình ảnh, vẽ nền trắng hình tròn
            g2.setColor(Color.WHITE);
            g2.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
        }

        g2.setColor(borderColor);

        // Vẽ viền hình tròn
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawOval(0, 0, diameter, diameter);
    }
    
    public void setImage(Icon image) {
        this.image = image;
        // Kích hoạt việc vẽ lại để thay đổi có hiệu lực ngay lập tức
        repaint();
    }

    private Image toImage(Icon icon) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return image;
    }

    private Rectangle getAutoSize(Icon icon) {
        return new Rectangle(0, 0, icon.getIconWidth(), icon.getIconHeight());
    }
    
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        // Kích hoạt việc vẽ lại để thay đổi có hiệu lực ngay lập tức
        repaint();
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        // Kích hoạt việc vẽ lại để thay đổi có hiệu lực ngay lập tức
        repaint();
    }

}
