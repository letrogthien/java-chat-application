package com.winform.views.homeViews;


import com.winform.models.Messagetype;
import com.winform.models.User;
import com.winform.swing.ScrollBar;
import com.winform.utills.Utills;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Date;

import javax.swing.*;
import javax.swing.JScrollBar;
import lombok.Data;
import net.miginfocom.swing.MigLayout;


@Data
public class Chat_Body extends javax.swing.JPanel {

    private User user;
    
    
    
    
  

    public void clearMessages() {
    body.removeAll(); // Xóa tất cả các component trong 'body'
    body.revalidate(); // Làm mới bố cục sau khi xóa
    body.repaint(); // Yêu cầu vẽ lại để phản ánh sự thay đổi
}
    
    public Chat_Body() {
        initComponents();
        init();
    }
    
    
    
    

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(String text, User user,Date time, Icon... image) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        ImageIcon avatarIcon = null;
            if (user.getAvatar() != null && !user.getAvatar().trim().isEmpty()) {
                avatarIcon = Utills.base64ToImageIcon(user.getAvatar());
            } else {
                avatarIcon = new ImageIcon(getClass().getResource("/img/icons8-user-50.png"));
            }
        item.setImageProfile(avatarIcon);
        item.setText(text);
        item.setImage(image);
        item.setTime(time.toString());
        item.setUserProfile(user.getUserName());
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemLeft(String text, User user, String[] image,Date time) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        ImageIcon avatarIcon = null;
            if (user.getAvatar() != null && !user.getAvatar().trim().isEmpty()) {
                avatarIcon = Utills.base64ToImageIcon(user.getAvatar());
            } else {
                avatarIcon = new ImageIcon(getClass().getResource("/img/icons8-user-50.png"));
            }
        item.setImageProfile(avatarIcon);
        item.setText(text);
        item.setImage(image);
        item.setTime(time.toString());
        item.setUserProfile(user.getUserName());
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }
    public void addItemLeft(String text, User user, Date time) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        // Kiểm tra xem nội dung có phải là chuỗi Base64 không
        if (Utills.isBase64(text)) {
            // Nếu có, giả định text là dữ liệu hình ảnh được mã hóa Base64
            // và chuyển đổi nó thành Icon để hiển thị
            ImageIcon imageIcon = Utills.base64ToImageIcon(text);
            item.setImage(new Icon[]{imageIcon}); // Thiết lập hình ảnh cho tin nhắn
        } else {
            // Nếu không, thiết lập text cho tin nhắn
            item.setText(text);
        }
        ImageIcon avatarIcon = null;
            if (user.getAvatar() != null && !user.getAvatar().trim().isEmpty()) {
                avatarIcon = Utills.base64ToImageIcon(user.getAvatar());
            } else {
                avatarIcon = new ImageIcon(getClass().getResource("/img/icons8-user-50.png"));
            }
        item.setImageProfile(avatarIcon);
        item.setUserProfile(user.getUserName()); // Thiết lập thông tin người dùng cho tin nhắn
        item.setTime(time.toString()); // Thiết lập thời gian cho tin nhắn

        body.add(item, "wrap, w 100::80%"); // Thêm tin nhắn vào bảng chat
        body.revalidate();
        body.repaint();
        scrollToBottom(); // Cuộn đến cuối cùng sau khi thêm tin nhắn mới
    }

    public void addItemFile(String text, String user, String fileName, String fileSize,Date time) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setFile(fileName, fileSize);
        item.setTime(time.toString());
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(String text, Messagetype messageType,Date time, Icon...  images) {
        Chat_Right item = new Chat_Right();
        switch (messageType) {
        case TEXT:
            item.setText(text);
            break;
        case IMAGE:
            // Tại đây, bạn cần chuyển đổi content từ Base64 (hoặc một định dạng tùy chỉnh khác) sang Icon
            Icon image = Utills.base64ToImageIcon(text);
            item.setImage(image);
            break;
    } 
        item.setImage(images);
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
        item.setTime(time.toString());
        scrollToBottom();
    }

    public void addItemFileRight(String text, String fileName, String fileSize, Date time  ) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setFile(fileName, fileSize);
        item.setTime(time.toString());
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        line1 = new com.winform.swing.Line();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(28, 38, 50));
        body.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 827, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        line1.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(line1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(line1, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JPanel jPanel1;
    private com.winform.swing.Line line1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
