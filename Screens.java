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
import java.awt.image.BufferStrategy;
import java.awt.image.*;

public class Screens extends Canvas implements Runnable, MouseListener {
   
   public static int WIDTH = 1280;
   public static int HEIGHT = 960;
   public static int screenChoice;
   public static JFrame frame;
   public JPanel PlayPanel;
   public JPanel InstructionPanel;
   
   private boolean running;
   
   private void start(){
      if(running) return;
      running = true; 
      new Thread(this, "JeffsMainThread").start();
   }
   private void tick(){};
   
   private void render() {
      BufferStrategy bs = getBufferStrategy();
      if (bs == null) {
         createBufferStrategy(3);
         return;
      }
      
      Graphics g = bs.getDrawGraphics();
      
      ///////////////////////////
      
      g.setColor(Color.RED);
      g.fillRect(0,0,WIDTH,HEIGHT);
      
      // stuff to draw for game //
      
      g.dispose();
      bs.show();
    }
   
   private void stop(){
      if(!running) return;
      running = false;
   }
   
   public void run() {
      double target = 60.0;
      double nsPerTick = 1000000000.0/ target;
      long lastTime = System.nanoTime();
      long timer = System.currentTimeMillis();
      double unprocessed = 0.0;
      int fps = 0;
      int tps = 0;
      boolean canRender = false;
      
      while(running) {
         long now = System.nanoTime();
         unprocessed += (now - lastTime) / nsPerTick;
         lastTime = now;
         
         if(unprocessed >= 1.0) {
            tick();
            unprocessed--;
            tps++;
            canRender = true;
         } else canRender = false;
         
         try {
            Thread.sleep(1);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         
         if(canRender) {
            fps++;
         }
         
         if(System.currentTimeMillis() - 1000 > timer) {
            timer += 1000;
            System.out.printf("FPS: %d | TPS: %d\n",fps,tps);
            fps = 0;
            tps = 0;
         }
       }  
       System.exit(0);
   }

      
   public static void main(String[] args) throws IOException
   {
      screenRun();
   }

   public static void screenRun() throws IOException
   {
      boolean playing = true;
      Screens menus = new Screens(0);
   }
   
   public Screens(int choice)
   {
      screenChoice = choice;
      Drawing draw = new Drawing();
      frame = new JFrame("Jeff Bezos' adventure time");
      this.display(); 
//      ButtonCreate b = new ButtonCreate(130,280,460,150,0,PlayPanel);
      frame.setSize(WIDTH,HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setFocusable(true);
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
         start();
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
