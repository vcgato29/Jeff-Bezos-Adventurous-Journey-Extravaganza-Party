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
   Drawing draw = new Drawing();
   public game()
   {

      Screens.frame.setSize(1280,960);
      Screens.frame.add(draw);
      Screens.frame.setVisible(true);
      Screens.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
         g.drawRect(640,370,30,30);
      } 
    }  
}   
   