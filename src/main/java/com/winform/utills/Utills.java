 
package com.winform.utills;
        
        
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.swing.ImageIcon;

        
public class Utills {
    public static boolean isBase64(String str) {
    return (str.length() % 4 == 0) && str.matches("^[A-Za-z0-9+/]+={0,2}$");
}
    public static String encodeFileToBase64Binary(File file) throws IOException {
    String encodedFile = null;
    try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        encodedFile = Base64.getEncoder().encodeToString(bytes);
    }
    return encodedFile; 
}
    public static ImageIcon base64ToImageIcon(String base64) {
    byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
    ImageIcon imageIcon = new ImageIcon(imageBytes);
    return imageIcon;
}
}
