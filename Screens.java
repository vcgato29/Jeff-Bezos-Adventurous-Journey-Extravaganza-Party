import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Object;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.math;


public class Screens extends Canvas implements Runnable, MouseListener {
   
   public static final String TITLE = "JEff";
   public static final int WIDTH = 1280;
   public static final int HEIGHT = 960;
   public static int screenChoice;
   public static JFrame frame;
   public JPanel PlayPanel;
   public JPanel InstructionPanel;
   private Texture texture;
   private Texture background;
   public static Screens INSTANCE; 

   
   private boolean running;

      
   
   private void start(){
      if(running) return;
      running = true; 
      new Thread(this, "JeffsMainThread").start();
   }
   
   public Screens() {
        texture = new Texture("sniper blue");
        background = new Texture("background");
        }

         
   
   private void render() {
      BufferStrategy bs = getBufferStrategy();
      if (bs == null) {
         createBufferStrategy(3);
         return;
      }
      
      Graphics g = bs.getDrawGraphics();
      Graphics2D g2d = (Graphics2D) g;
      
      g2d.translate(-6, -28);
      ///////////////////////////
      
      g2d.setColor(Color.RED);
      g2d.fillRect(0,0,WIDTH,HEIGHT);
      background.render(g2d,0,0);
      texture.render(g2d,100,100);
      
      // stuff to draw for game //
      
      g.dispose();
      bs.show();
    }
   
   private void stop(){
      if(!running) return;
      running = false;
   }
   
   public void run() {
      running = true;
      requestFocus();
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
            render();
            
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
      boolean playing = true;
      Screens menus = new Screens(0);    
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
      frame.addWindowListener(new WindowAdapter() { @Override public void windowClosing( WindowEvent e ) { e.getWindow().dispose(); } });

      
      
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
         Screens game = new Screens();
         JFrame frame = new JFrame("Jeff");
         
         frame.add(game);
         frame.setSize(WIDTH,HEIGHT);
         frame.setFocusable(true);
         frame.setResizable(false);
         frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                game.stop();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();
 

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
            frame.dispose();
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
