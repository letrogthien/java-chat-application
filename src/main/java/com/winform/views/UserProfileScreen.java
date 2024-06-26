 
package com.winform.views;
import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.swing.AvatarBorderComponent;
import com.winform.swing.AvatarBox;
import com.winform.views.homeViews.Home;
import com.winform.views.homeViews.Menu_Right;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.border.Border;
public class UserProfileScreen extends JDialog {

    private User user;
    private JButton btnBack, btnUpdate;
    private JLabel lblUsername, lblFullName, lblEmail, lblPhone, lblNickName;
    private JTextField txtFullName, txtEmail, txtPhone, txtNickName;
    private JPasswordField txtPassword;
    private JPanel userInfoPanel, updatePanel;

    public UserProfileScreen(Frame parent, User user) {
        super(parent, "User Profile", true); // Thiết lập JDialog là modal và có tiêu đề

        this.user = user;
        initComponents();
        setUpGUI();

        // Cài đặt kích thước và vị trí của JDialog
        setSize(350, 450); 
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void setUpGUI() {
        // Set layout for the main panel
        this.setLayout(new BorderLayout());

        // Card Panel with semi-transparent background and rounded corners
        JPanel cardPanel = new JPanel(new BorderLayout(5, 5));
        cardPanel.setBackground(new Color(255, 255, 255)); 

        // User Info Panel
        userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 0, 0, 0);

        AvatarBox avatarBox = new AvatarBox();
        avatarBox.setPreferredSize(new Dimension(150, 150)); // Adjust the size as needed
        avatarBox.setMinimumSize(new Dimension(150, 150)); // Adjust the size as needed
        avatarBox.setMaximumSize(new Dimension(150, 150)); // Adjust the size as needed
        
        

        UserService userService = new UserService();
        String avatarBase64 = userService.getAvatarByUsername(user.getUserName());
        if (avatarBase64 != null && !avatarBase64.isEmpty()) {
            avatarBox.setImage(base64ToImageIcon(avatarBase64)); // sử dụng avatar từ server
        } else {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/img/avatar_default.png"));
            avatarBox.setImage(defaultIcon); // sử dụng avatar mặc định
        }

        avatarBox.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));

                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ImageIcon newIcon = new ImageIcon(file.getAbsolutePath());
                    avatarBox.setImage(newIcon); // Set trực tiếp ImageIcon vào AvatarBox

                    // Example of how to update avatar on the server or locally
                    UserService userService = new UserService();
                    boolean success = userService.updateAvatarByUsernameUsingBase64(user.getUserName(), file); // Implement this method in UserService
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Avatar updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update avatar.");
                    }
                }
            }
        });
        
        AvatarBorderComponent avatarBorderComponent = new AvatarBorderComponent(avatarBox);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;
        userInfoPanel.add(avatarBorderComponent, gbc);

        
        gbc.gridx  = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.LINE_START;

        // Add user info fields
        lblUsername = new JLabel(user.getUserName());
        lblUsername.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.insets = new Insets(10, 10, 0, 0); 
        userInfoPanel.add(lblUsername, gbc);
        
        
        lblFullName = new JLabel("Full Name: ");
        gbc.gridx  = 0;
        gbc.gridy = 2;
        userInfoPanel.add(lblFullName, gbc);
        txtFullName = new JTextField(user.getFullName());
        gbc.gridx = 2;
        userInfoPanel.add(txtFullName, gbc);

        lblEmail = new JLabel("Email: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        userInfoPanel.add(lblEmail, gbc);
        txtEmail = new JTextField(user.getEmail());
        gbc.gridx = 2;
        userInfoPanel.add(txtEmail, gbc);

        lblPhone = new JLabel("Phone: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        userInfoPanel.add(lblPhone, gbc);
        txtPhone = new JTextField(user.getPhone());
        gbc.gridx = 2;
        userInfoPanel.add(txtPhone, gbc);

        lblNickName = new JLabel("Nick Name: ");
        gbc.gridx = 0;
        gbc.gridy = 8;
        userInfoPanel.add(lblNickName, gbc);
        txtNickName = new JTextField(user.getNickName());
        gbc.gridx = 2;
        userInfoPanel.add(txtNickName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        userInfoPanel.add(new JLabel("Password: "), gbc);
        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('*');
        gbc.gridx = 2;
        userInfoPanel.add(txtPassword, gbc);

        // Add a separate panel for update section
        updatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        updatePanel.setOpaque(false); // Transparent to show the cardPanel color
        btnUpdate = new JButton("Update Information");
        Color customColor = new Color(0, 102, 102); // Tạo một màu mới từ giá trị RGB
        btnUpdate.setBackground(customColor); // Đặt màu nền cho nút bằng màu tạo từ giá trị RGB
        btnUpdate.setForeground(Color.WHITE);
        updatePanel.add(btnUpdate);

        cardPanel.add(userInfoPanel, BorderLayout.CENTER);
        cardPanel.add(updatePanel, BorderLayout.SOUTH);


        // Panel container to center the card panel and the back button
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        
        // Adding cardPanel to main dialog window
        this.add(cardPanel, BorderLayout.CENTER);


        // Event for Update button
       // Event for Update button
        btnUpdate.addActionListener((ActionEvent e) -> {
            String fullName = txtFullName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String nickName = txtNickName.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            // Kiểm tra nếu bất kỳ trường nào được bỏ trống
            if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || nickName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.", "Thông Báo", JOptionPane.WARNING_MESSAGE);
            } else {
                user.setFullName(fullName);
                user.setEmail(email);
                user.setPhone(phone);
                user.setNickName(nickName);
                user.setPassword(password);

                boolean success = userService.updateUser(user.getUserName(), user);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập Nhật Thông Tin Người Dùng Thành Công.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin người dùng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public ImageIcon base64ToImageIcon(String base64) {
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
        ImageIcon imageIcon = new ImageIcon(imageBytes);
        return imageIcon;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
