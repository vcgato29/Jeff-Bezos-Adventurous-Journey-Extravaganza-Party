import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.image.BufferStrategy;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Object;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class Floor
{
   private Texture brick;
   private Texture startBrick;
   public int width = 32;
   public int height = 32;
   public int brickNum;
   public int startX;
   public int y;
   
   public Floor(int sX, int sY, int bN, Graphics2D g2d)
   {
      brickNum = bN;
      startX = sX;
      y = sY;
      startBrick = new Texture("BRICK1 w_spikes");
      startBrick.render(g2d,startX,y);

      for(int i = 0; i <= brickNum; i++)
      {
         startX += width;
         brick = new Texture("BRICK1");
         brick.render(g2d,startX,y);
      }
   }
   
   public void upLevel(int upStart, int upLength, int cU, Graphics2D g2d)
   {
      int countUps = cU;
      int yNewFloor = y;
      yNewFloor -= (countUps * 32);
      new Floor(upStart, yNewFloor, upLength, g2d);
   }
} 