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

public class Screens implements MouseListener
{
   public static int screenChoice;
   public static JFrame frame;
   
   public Screens(int choice)
   {
      screenChoice = choice;
      Drawing draw = new Drawing();
      frame = new JFrame("Jeff Bezos' adventure time");
      frame.setSize(1280,960);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.addMouseListener(this);
      frame.add(draw);
      frame.toFront();
      frame.requestFocus();
      frame.setVisible(true);
      
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
         System.out.println("menu");
      }
      
      if(screenChoice == 1)
      {
         new game();
         System.out.println("doing stuff");
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
      this.display();
      int x = e.getX();
      int y = e.getY();
      if((x > 200 && x < 300) && screenChoice == 0)
      {
         if(y > 200 && y < 300)
         {
            screenChoice = 1;
            System.out.println("its poppin");
         }
      }
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
         int mouseX = MouseInfo.getPointerInfo().getLocation().x;
         int mouseY = MouseInfo.getPointerInfo().getLocation().y;
         System.out.println(mouseX);
         if (Screens.screenChoice != 1) 
         {
            BufferedImage img=ImageIO.read(new File("images/Title Screenz.png"));
            g.drawImage(img,0,0,null); 
            if(mouseX >= 200 && mouseX < 300)
            {
               if(mouseY >= 200 && mouseY < 300)
               {
               System.out.println("yellowoowowow");
                  g.setColor(Color.YELLOW);
                  g.drawRect(200,200,100,100);
               }
            }else
            {
            System.out.println("black");
               g.setColor(Color.RED);
               g.drawRect(130,280,460,150);
            }
         }
      }   
      catch(IOException e) 
      {
         System.exit(1);
      }
   } 
}
