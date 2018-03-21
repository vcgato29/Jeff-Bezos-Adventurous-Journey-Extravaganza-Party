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

public class main1 implements MouseListener {
   int gamestate = 1;
   int x;
   int y;
   BufferedImage img=ImageIO.read(new File("images/title.png"));
   Drawing draw = new Drawing();
   
    public static void main(String[] args) throws IOException
    {
         new main1();
    }

    public main1() throws IOException
    {
      JFrame frame = new JFrame("Jeff Bezos' adventure time");
      frame.setSize(1280,960);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      draw.addMouseListener(this);
      frame.add(draw);
      frame.setVisible(true);
    }
    class Drawing extends JComponent {
      public void paint(Graphics g){
         g.drawImage(img,0,0,null);
        
         
      }
    }
    
    public void mouseClicked (MouseEvent e) {
        if (e.getX() > 268 && e.getY() > 145 && e.getX() < 745 && e.getY() < 425)
            new Game();
   }
    public void mousePressed (MouseEvent e) {}
    public void mouseReleased (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
      
}

