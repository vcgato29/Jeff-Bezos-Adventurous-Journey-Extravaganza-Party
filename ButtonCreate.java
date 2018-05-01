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

public class ButtonCreate implements MouseListener
{
   public int buttonHeight;
   public int buttonWidth;
   public int buttonX;
   public int buttonY;
   public int buttonStyle;
   public JFrame buttonFrame;
   
   public ButtonCreate(int xLoc, int yLoc, int width, int height, JFrame frameChoice)
   {
      buttonX = xLoc;
      buttonY = yLoc;
      buttonHeight = height;
      buttonWidth = width;
      buttonStyle = 0;
      buttonFrame = frameChoice;
   }
   
   public ButtonCreate(int xLoc, int yLoc, int width, int height, int style, JFrame frameChoice)
   {
      buttonX = xLoc;
      buttonY = yLoc;
      buttonHeight = height;
      buttonWidth = width;
      buttonStyle = style;
      buttonFrame = frameChoice;
   }
   
   public void paintComponent(Graphics g)
   {
      g.drawString("TEST BUTTON" , buttonX, buttonY + buttonHeight);
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