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

public class Brick
{
   public Texture brick;
   public boolean spikes;
   public int x;
   public int y;
   public int width = 32;
   public int height = 32;
   public Brick[] floor = new Brick[1000];
   public boolean makeFloor;
   
   public Brick(int brickX,int brickY,boolean type,boolean mF)
   {
      brickX = x;
      brickY = y;
      spikes = type;
      makeFloor = mF;
      brick = new Texture("BRICK1");
      if(makeFloor == true && spikes == false)
      {
         for(int i = 0; i < floor.length; i++)
         {
            floor[i] = new Brick(x + 32, y + 32, false, false);
            
         }
      }
   }
}