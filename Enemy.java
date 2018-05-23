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


public class Enemy
{
   public int health; 
   public int damage; 
   public int enemyX;
   public int enemyY;
   public int type;
   boolean isAlive;
   public int enemySpeed;
   public Texture appleGoomba = new Texture("apple logo goomba static");

   public Enemy (int floorC, int eX, int eLevel, int eSpeed, int type, Graphics2D g2d)	
   {
      enemyY = 600 - eLevel * 32;
      type = type;
      floorC += eSpeed;
      enemyX = eX + floorC;
      if	(type	==	1)
      {
         health =	100;
         damage =	50;
      }
      else if(type == 2)
      {
         health =	150;
         damage =	50;
      }
      else if (type == 3)
      {
         health =	200;
         damage =	50;
      }
      else if (type == 4)
      {
         health =	250;
         damage =	100;
      }
      else if (type == 5)
      {
         health =	300;
         damage =	100;
      }
      appleGoomba.render(g2d, enemyX, enemyY);
   }
}