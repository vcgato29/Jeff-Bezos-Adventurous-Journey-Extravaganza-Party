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
import java.util.concurrent.TimeUnit;


public class game
{

   long x = 640;
   long y = 370;
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
   
   public long elapsed(long strt, long finish) {
      return finish - strt;
   }
   
   public void jump() {
      long start = y;
      long sleeptime = 50L;
      long begin = System.currentTimeMillis()/1000L;
      y = 15*elapsed(begin,time()) - 5*elapsed(begin,time())*elapsed(begin,time()); 
      while (start != y) {
         TimeUnit.MILLIS.sleep(sleeptime);
         y = 15*elapsed(begin,time()) - 5*elapsed(begin,time())*elapsed(begin,time()); 
      }
   }
         
   
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
         g.drawRect((int)x,(int)y,30,30);
         jump();
      } 
    }  
}   
   