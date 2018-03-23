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

public class Screens implements MouseListener
{
   int screenChoice;
   
   public Screens(int choice)
   {
      screenChoice = choice;
   }
   
   public void changeScreen(int newScreen)
   {
      screenChoice = newScreen;
   }
   
   public int getScreen()
   {
      return screenChoice;
   }
   
   public void display()
   {
      if(screenChoice == 0)
      {
         Drawing draw = new Drawing();
         JFrame frame = new JFrame("Jeff Bezos' adventure time");
         frame.setSize(1280,960);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         draw.addMouseListener(this);
         frame.add(draw);
         frame.setVisible(true);

      }
   }
 
   public void mouseClicked(MouseEvent e)
   {
   }
   public void mousePressed(MouseEvent e)
   {
   }
   public void mouseReleased(MouseEvent e)
   {
   }
   public void mouseEntered(MouseEvent e)
   {
   }
   public void mouseExited(MouseEvent e)
   {
   }
}         

class Drawing extends JComponent
{
   public void paint(Graphics g)
   {
      try   
      {
         BufferedImage img=ImageIO.read(new File("images/title.png"));
         g.drawImage(img,0,0,null);  
      }   
      catch(IOException e) 
      {
         System.exit(1);
      }
   } 
}
