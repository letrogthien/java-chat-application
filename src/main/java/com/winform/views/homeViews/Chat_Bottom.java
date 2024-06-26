package com.winform.views.homeViews;

import com.winform.customComponent.Panel_More;
import com.winform.event.EventChat;
import com.winform.event.PublicEvent;
import com.winform.models.Messagetype;
import com.winform.swing.JIMSendTextPane;
import com.winform.swing.ScrollBar;
import com.winform.utills.Utills;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JFileChooser;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
public class Chat_Bottom extends javax.swing.JPanel {

    private EventChat eventChat;
    private MigLayout mig;
    private Panel_More panelMore;

    public void chat1(EventChat eventChat) {
        this.eventChat = eventChat;
    }

    public Chat_Bottom() {
        initComponents();
        init();
    }

    private void init() {
        mig = new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2[]0");
        setLayout(mig);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        
        // Set hint text
        txt.setHintText("Write Message Here ...");

        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    ke.consume();  // Prevent newline character from being inserted
                    sendMessage(txt);
                }
            }
        });

        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(229, 229, 229));
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]3[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/icon/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sendMessage(txt);
            }
        });
        //
        JButton cmdChooseFile = new JButton();
cmdChooseFile.setBorder(null);
cmdChooseFile.setContentAreaFilled(false);
cmdChooseFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
cmdChooseFile.setIcon(new ImageIcon(getClass().getResource("/icon/link.png"))); // Gỉa sử bạn có icon là attach.png
cmdChooseFile.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent ae) {
        chooseAndSendFile();
    }
});

panel.add(cmdChooseFile);
//test
        JButton cmdMore = new JButton();
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/icon/more_disable.png")));
        cmdMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelMore.isVisible()) {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/icon/more_disable.png")));
                    panelMore.setVisible(false);
                    mig.setComponentConstraints(panelMore, "dock south,h 0!");
                    revalidate();
                } else {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/icon/more.png")));
                    panelMore.setVisible(true);
                    mig.setComponentConstraints(panelMore, "dock south,h 170!");
                    revalidate();
                }
            }
        });
        
        panel.add(cmdMore);
        panel.add(cmd);
        add(panel, "wrap");
        panelMore = new Panel_More();
        panelMore.setVisible(false);
        add(panelMore, "dock south,h 0!");  //  set height 0
    }
private void chooseAndSendFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
    fileChooser.addChoosableFileFilter(filter);

    int option = fileChooser.showOpenDialog(this);
    if (option == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            // Chuyển đổi file sang Base64
            String contentBase64 = Utills.encodeFileToBase64Binary(selectedFile);
            // Gửi tin nhắn với nội dung là chuỗi Base64 và setType là IMAGE
            eventChat.sendMessage(contentBase64, Messagetype.IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi
        }
    }
}
    private void sendMessage(JIMSendTextPane txt) {
        String text = txt.getText().trim();
        if (!text.equals("")) {
            eventChat.sendMessage(text,Messagetype.TEXT);
            txt.setText("");
            txt.grabFocus();
            refresh();
        } else {
            txt.grabFocus();
        }
    }

    public void refresh() {
        revalidate();
        repaint(); // Ensures UI is repainted
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
