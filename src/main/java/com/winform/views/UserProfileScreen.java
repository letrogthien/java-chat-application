 
package com.winform.views;
import com.winform.models.User;
import com.winform.services.UserService;
import com.winform.swing.AvatarBox;
import com.winform.views.homeViews.Home;
import com.winform.views.homeViews.Menu_Right;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        setSize(500, 500);
        setLocationRelativeTo(parent);
    }

    private void setUpGUI() {
        // Set layout for the main panel
        this.setLayout(new BorderLayout());

        // Background Image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/img/backround.jpg"))) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = (ImageIcon) this.getIcon();
                if (icon != null) {
                    Image image = icon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        background.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);

        // Card Panel with rounded corners
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBackground(new Color(255, 255, 255, 200)); // Set background color to white with some transparency
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8, true));
        cardPanel.setPreferredSize(new Dimension(400, 300)); // Fixed size for the card panel

        // User Info Panel
        userInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userInfoPanel.setOpaque(false);

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

        userInfoPanel.add(avatarBox);
        userInfoPanel.add(new JLabel(" "));

        // Add user info fields
        lblUsername = new JLabel("Username: " + user.getUserName());
        lblFullName = new JLabel("Full Name: ");
        txtFullName = new JTextField(user.getFullName());

        lblEmail = new JLabel("Email: ");
        txtEmail = new JTextField(user.getEmail());

        lblPhone = new JLabel("Phone: ");
        txtPhone = new JTextField(user.getPhone());

        lblNickName = new JLabel("Nick Name: ");
        txtNickName = new JTextField(user.getNickName());

        userInfoPanel.add(lblUsername);
        userInfoPanel.add(new JLabel()); // Empty label for alignment
        userInfoPanel.add(lblFullName);
        userInfoPanel.add(txtFullName);
        userInfoPanel.add(lblEmail);
        userInfoPanel.add(txtEmail);
        userInfoPanel.add(lblPhone);
        userInfoPanel.add(txtPhone);
        userInfoPanel.add(lblNickName);
        userInfoPanel.add(txtNickName);
        userInfoPanel.add(new JLabel("Password: "));
        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('*');
        userInfoPanel.add(txtPassword);

        // Add a separate panel for update section
        updatePanel = new JPanel();
        updatePanel.setOpaque(false);
        btnUpdate = new JButton("Update Information");
        updatePanel.add(btnUpdate);

        cardPanel.add(userInfoPanel, BorderLayout.CENTER);
        cardPanel.add(updatePanel, BorderLayout.SOUTH);

        // Panel container to center the card panel and the back button
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        // Back Button at the top left corner of the main panel
        btnBack = new JButton("Back");
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(btnBack);

        centerPanel.add(backPanel, BorderLayout.NORTH);

        // Use another JPanel to center the card panel within the available space
        JPanel cardWrapperPanel = new JPanel(new GridBagLayout());
        cardWrapperPanel.setOpaque(false);
        cardWrapperPanel.add(cardPanel);

        centerPanel.add(cardWrapperPanel, BorderLayout.CENTER);
        background.add(centerPanel, BorderLayout.CENTER);

        // Event for Back button
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng JDialog
            }
        });

        // Event for Update button
        btnUpdate.addActionListener((ActionEvent e) -> {
            user.setFullName(txtFullName.getText());
            user.setEmail(txtEmail.getText());
            user.setPhone(txtPhone.getText());
            user.setNickName(txtNickName.getText());
            user.setPassword(new String(txtPassword.getPassword()));

            boolean success = userService.updateUser(user.getUserName(), user);
            if (success) {
                JOptionPane.showMessageDialog(null, "Cập Nhật Thông Tin Người Dùng Thành Công.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user information", "Error", JOptionPane.ERROR_MESSAGE);
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
