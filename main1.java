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


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class main1
{
   int gamestate = 1;
   int x;
   int y;
         
   public static void main(String[] args) throws IOException
   {
      new main1();
   }

   public main1() throws IOException
   {
      boolean playing = true;
      Screens menus = new Screens(0);
//      while(playing)
      {
         menus.display();
      }
   }
}