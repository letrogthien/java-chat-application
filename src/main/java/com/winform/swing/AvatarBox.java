    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.winform.swing;

    import java.awt.Dimension;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Image;
    import java.awt.Point;
    import java.awt.Rectangle;
    import java.awt.RenderingHints;
    import java.awt.geom.Ellipse2D;
    import java.awt.image.BufferedImage;
    import javax.swing.Icon;
    import javax.swing.ImageIcon;
    import javax.swing.JLayeredPane;

    public class AvatarBox extends JLayeredPane {
        private Icon image;

        public Icon getImage() {
            return image;
        }

        public void setImage(Icon image) {
            this.image = image;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            if (image != null) {
                Graphics2D g2 = (Graphics2D) grphcs.create();

                // Chuẩn bị hình ảnh và vùng vẽ
                Image img = toImage(image);
                Rectangle size = getAutoSize(image);
                int diameter = Math.min(size.width, size.height);

                // Tạo ảnh tạm để cắt hình tròn
                BufferedImage circleBuffer = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2dTemp = circleBuffer.createGraphics();

                // Antialiasing để hình vẽ mượt mà
                g2dTemp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Vẽ hình tròn trên bức ảnh tạm
                g2dTemp.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
                g2dTemp.drawImage(img, 0, 0, diameter, diameter, null);
                g2dTemp.dispose();


                int x = getWidth() / 2 - (diameter / 2);
                int y = getHeight() / 2 - (diameter / 2);

                // Vẽ bức ảnh hình tròn vào pane 
                g2.drawImage(circleBuffer, x, y, this);
                g2.dispose();
            }
        }

        private Rectangle getAutoSize(Icon image) {
            int w = getWidth();
            int h = getHeight();
            if (w > image.getIconWidth()) {
                w = image.getIconWidth();
            }
            if (h > image.getIconHeight()) {
                h = image.getIconHeight();
            }
            int iw = image.getIconWidth();
            int ih = image.getIconHeight();
            double xScale = (double) w / iw;
            double yScale = (double) h / ih;
            double scale = Math.min(xScale, yScale);
            int width = (int) (scale * iw);
            int height = (int) (scale * ih);
            int x = getWidth() / 2 - (width / 2);
            int y = getHeight() / 2 - (height / 2);
            return new Rectangle(new Point(x, y), new Dimension(width, height));
        }

        private Image toImage(Icon icon) {
            return ((ImageIcon) icon).getImage();
        }
    }
