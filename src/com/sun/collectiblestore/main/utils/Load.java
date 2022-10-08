package com.sun.collectiblestore.main.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Load {
    public static BufferedImage getSpriteAtlas(String name){
        BufferedImage img = null;
        InputStream is = Load.class.getResourceAsStream("/" + name);
        try{
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return img;
    }
}
