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
   public JPanel PlayPanel;
   public JPanel InstructionPanel;
   
   public Screens(int choice)
   {
      screenChoice = choice;
      Drawing draw = new Drawing();
      frame = new JFrame("Jeff Bezos' adventure time");
      this.display(); 
//      ButtonCreate b = new ButtonCreate(130,280,460,150,0,PlayPanel);
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
      this.display();
   }
   
   public int getScreen()
   {
      return screenChoice;
   }
   
   public void display()
   {
      if(screenChoice == 0)
      {  
         PlayPanel = new JPanel(new GridLayout(1,1));
         myJButton PlayButton = new myJButton(140,290,450,140,PlayPanel,"Play",this);
         InstructionPanel = new JPanel(new GridLayout(1,1));
         myJButton InstButton = new myJButton(740,290,430,140,InstructionPanel,"Instructions",this);
         frame.add(PlayPanel);
         frame.add(InstructionPanel);
         frame.repaint();
//         new ButtonCreate(140,290,440,130,0,PlayPanel);
         System.out.println("menu");
      }
      
      if(screenChoice == 1)
      {
         frame.remove(PlayPanel);
         frame.remove(InstructionPanel);
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
      int x = e.getX();
      int y = e.getY();
      if((x > 140 && x < 580) && screenChoice == 0)
      {
         if(y > 290 && y < 420)
         {
            screenChoice = 1;
            System.out.println("Choice: " + screenChoice);
            this.display();
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
      System.out.println("is it");
      try   
      {
         int mouseX = MouseInfo.getPointerInfo().getLocation().x;
         int mouseY = MouseInfo.getPointerInfo().getLocation().y;
         System.out.println("X " + mouseX);
         System.out.println("Y " + mouseY);
         if (Screens.screenChoice == 0) 
         {
            BufferedImage img=ImageIO.read(new File("images/Title Screenz.png"));
            g.drawImage(img,0,0,null); 
            Font titleFont = new Font("Serif", Font.ITALIC, 50);
            g.setFont(titleFont);
            g.drawString("Jeff Bezos' Adventurous Journey Extravaganza Party",120,140);
         }
      }   
      catch(IOException e) 
      {
         System.exit(1);
      }
   } 
}
