import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Texture {

    private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();

    private BufferedImage image;
    private String        fileName;
    public int           width, height;

    public Texture(String fileName) {
        this.fileName = fileName;
        BufferedImage oldTexture = texMap.get(fileName);
        if (oldTexture != null)
            this.image = oldTexture;
        else {
            try {
                System.out.println("Loading texture: " + fileName);
                this.image = ImageIO.read(new File("./images/" + fileName + ".png"));
                texMap.put(fileName, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public Texture(Texture spriteSheet, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        String key = spriteSheet.fileName + "_" + x + "_" + y;
        BufferedImage old = texMap.get(key);
        if (old != null) this.image = old;
        else this.image = spriteSheet.image.getSubimage(
                x * width - width,
                y * height - height,
                width, height);
    }

    public Texture(Texture spriteSheet, int x, int y, int size) {
        this(spriteSheet, x, y, size, size);
    }

    public void render(Graphics2D g, double x, double y) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public void render(Graphics2D g, int destX1, int destX2, int srcX1, int srcX2, int y) {
        g.drawImage(image, destX1, y, destX2, y + height, srcX1, 0, srcX2, height, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

}