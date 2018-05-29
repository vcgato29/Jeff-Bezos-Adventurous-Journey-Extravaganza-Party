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
import java.awt.Component.*;
import javax.swing.JComponent.*;

public class myJButton extends JPanel implements ActionListener
{  
   public JPanel buttonPanel;
   public String buttonName;
   public int buttonHeight;
   public int buttonWidth;
   public int buttonX;
   public int buttonY;
   public Screens buttonScreen;

   
   public myJButton(int xLoc, int yLoc, int w, int h, JPanel p, String t, Screens s)
   {
      buttonX = xLoc;
      buttonY = yLoc;
      buttonWidth = w;
      buttonHeight = h;
      buttonPanel = p;
      buttonName = t;
      buttonScreen = s;
      this.display();
   }
   
   public void display()
   {
      buttonPanel.setLocation(buttonX, buttonY);
      buttonPanel.setSize(buttonWidth, buttonHeight);
      JButton b = new JButton(buttonName);
      buttonPanel.add(b);
      b.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e)
   {
      String pressed = e.toString();
      System.out.println("Button pressed" + e.getSource());
      if(pressed.contains("Play"))
      {
         buttonScreen.changeScreen(1);
      }
      if(pressed.contains("Instructions"))
      {
         buttonScreen.changeScreen(2);
      }
      if (pressed.contains("Main Menu"))
      {
         buttonScreen.changeScreen(0);
      }
   }
}