/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author root
 */
public final class ImageLogic {
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    private static BufferedImage bytesToImage(byte image[]) throws Exception{
        BufferedImage bImageFromConvert = null;
        try{
            InputStream in = new ByteArrayInputStream(image);
            bImageFromConvert = ImageIO.read(in);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception ("Bad Request");
        } 
        return bImageFromConvert;
    }
    private static byte[] imageToBytes(BufferedImage image) throws Exception{
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception ("Bad Request");
        }
        return bytes;
    }
    public static byte[] getSmallImage(byte[] imageBytes) throws Exception{
        BufferedImage image = bytesToImage(imageBytes);
        image = resize(image, 256, 256);
        return imageToBytes(image);
    }
}
