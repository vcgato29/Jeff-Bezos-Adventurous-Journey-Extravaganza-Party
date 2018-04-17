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


public class game
{

   int x = 640;
   int y = 370;
   Drawing draw = new Drawing();
   public game()
   {

      Screens.frame.setSize(1280,960);
      Screens.frame.add(draw);
      Screens.frame.setVisible(true);
      Screens.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   } 
   
   public long time() {
      long time = System.currentTimeMillis()/1000L;
      return time;
   }
   
   public long elapsed(long start, long finish) {
      return start - finish;
   }
   
 //  public void jump() {
 //     int start = y;
 //     long start = System.currentTimeMillis()/1000L;
  //    y = 1/2(-9.8)*(elapsed(start,time()))**2+ 
      
   
   public static void main(String[] args) 
   {
      new game();
   }
    
   public class Drawing extends JComponent 
   {
      public void paint(Graphics g) 
      {
        
         g.setColor(Color.BLUE);
         g.drawLine(0,400,1280,400);
         g.drawRect(x,y,30,30);
      } 
    }  
}   
   