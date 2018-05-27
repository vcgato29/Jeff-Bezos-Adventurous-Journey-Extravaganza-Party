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
   public int mapLength;
   public int floorBase;
   public int[] floorMap;
   private int stepValue;
   public int y;
   public int countUps;
   public int upStart;
   public int yNewFloor;
         
   public Floor(int floorChange, int mL, int sValue, int fB, int[] fM, Graphics2D g2d)
   {    
      int level;
      int prevLevel = -1;
      int blockX;
      stepValue = sValue;
      floorMap = fM;
      floorBase = fB;
      mapLength = mL;
      brickNum = mapLength/width;
      for(int i = 0; i < floorMap.length; i++)
      {        
         level = floorMap[i];
         blockX = floorChange + i*32;
         for (int j=0; j < level;j++){
            brick = new Texture("BRICK1");
            brick.render(g2d,blockX,floorBase - j*height);
         }
         if (level > prevLevel){
            brick = new Texture("BRICK1 w_spikes");
            brick.render(g2d,blockX,floorBase - level*height);
         }
         else {
            brick = new Texture("BRICK1");
            brick.render(g2d,blockX,floorBase - level*height);
         }
         prevLevel = level;            
      }
   }
   
   public void move(int floorChange, Graphics2D g2d)
   {
      new Floor(floorChange, mapLength,stepValue, floorBase, floorMap, g2d);   
   }
        
   // get the height of the floor at pixel pX given floorChange
   public int getFloorHeight(int floorChange, int pX)
   {  
      int startX= ((pX+width)+Math.abs(floorChange))/32;
//      System.out.println(startX + "-" + pX + "-" + floorChange);
      try{
//      System.out.println("height=" + floorMap[startX]*height);
         return floorMap[startX]*height;
      }
      catch (Exception e) {
         return -1;
      }
   }
} 