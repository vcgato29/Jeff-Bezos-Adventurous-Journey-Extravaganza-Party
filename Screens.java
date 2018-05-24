
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



public class Screens extends Canvas implements Runnable, MouseListener, KeyListener {
   
   public static final String TITLE = "JEff";
   public static final int WIDTH = 1280;
   public static final int HEIGHT = 960;
   public int floorChange = 0;
   public static int screenChoice;
   public static JFrame frame;
   public JPanel PlayPanel;
   public JPanel InstructionPanel;
   private Texture texture;
   private Texture background;
   public static Screens INSTANCE; 
   public int playerX;
   public int playerY;
   public int armX;
   public int armY;
   public Texture jeffy;
   public Texture jeffyArm;
   public int x = 0;
   public int y;
   public int gravity = 2;
   public int yvel = 0;
   public int gravMag = 0;
   public int mouseX= MouseInfo.getPointerInfo().getLocation().x;
   public int mouseY= MouseInfo.getPointerInfo().getLocation().y;
   public boolean shoot = false;
   public int speed = 30;
   public int count = 0;
   public Player jeff = new Player();
   public int mapLength = 3200;
   public int stepSize = 4;
   public int[] floorTracker = new int[mapLength/stepSize];
   public int enemyCount = 0;



   
   
   
   private boolean running;

      
   
   private void start(){
      if(running) 
         return;
      running = true; 
      new Thread(this, "JeffsMainThread").start();
      addKeyListener(this);
      addMouseListener(this);
   }
   
   public Screens() {
      
      texture = new Texture("sniper blue");
      background = new Texture("background");
      jeff.jeffy = new Texture("bezos");
      jeff.jeffyArm = new Texture("bezosarm");
      jeff.x = 93;
      jeff.y = 480;
      for(int i = 0; i < floorTracker.length; i++)
      {
         floorTracker[i] = 0;
      }
   
   }

         
   
   public void render() {
      BufferStrategy bs = getBufferStrategy();
      if (bs == null) {
         createBufferStrategy(3);
         return;
      }
      Graphics g = bs.getDrawGraphics();
      Graphics2D g2d = (Graphics2D) g;
      
      g2d.translate(-6, -28);
      ///////////////////////////
      Shoot shooter = new Shoot(g2d);
      
      jeff.armX = jeff.x + 45;
      jeff.armY = jeff.y + 26;
      mouseX= MouseInfo.getPointerInfo().getLocation().x;
      mouseY= MouseInfo.getPointerInfo().getLocation().y; 
      g2d.setColor(Color.RED);
      g2d.fillRect(0,0,WIDTH,HEIGHT);
      background.render(g2d,0,0);
      texture.render(g2d,mouseX-125,mouseY-25);
      Floor f = new Floor(floorChange, 600, 100,g2d);
      f.upLevel(floorChange, 600,20,1,floorTracker,g2d);
      f.upLevel(floorChange, 700,10,2,floorTracker,g2d);
      f.upLevel(floorChange, 1500,10,1,floorTracker,g2d);
      if (jeff.y < 528-((floorTracker[f.getFloorHeight(floorChange,jeff.x)])*32))
         jeff.y += (gravity*gravity); 
     
     
      if (yvel > 0) {
         gravMag -= (gravity*gravity);
         yvel = yvel + gravMag;
      }
      
      
      if (jeff.y < 528-((floorTracker[f.getFloorHeight(floorChange,jeff.x)])*32)) {
         gravMag = 0;
         yvel=0;
      }
      jeff.y = jeff.y-yvel;
   
      if (shoot) {
         count++;
         shooter.fire(mouseX-115,mouseY-25,g2d);
         shooter.bullet.render(g2d,shooter.x+speed,shooter.y);
         speed += 30;
         if (count > 42) {
            count = 0;
            speed = 30;
            shoot = false;
         }
      }
         
   
      jeff.jeffy.render(g2d,jeff.x,jeff.y);
   
      jeff.jeffyArm.render(g2d,jeff.armX,jeff.armY);
      jeff.checkCollision(floorChange,floorTracker,f);
      
      Enemy goomb1 = new Enemy(floorChange,750,3,0,1,enemyCount,g2d);
      goomb1.turnAround(goomb1,floorChange,floorTracker,f,g2d);
      System.out.println(floorTracker[f.getFloorHeight(floorChange, goomb1.enemyX)]);
      System.out.println(jeff.getPlayerHeight());
      enemyCount++;
   
      floorChange -= 4;
      //System.out.println((Math.abs(floorChange)+(playerX+47))/4 + "-" + floorTracker[(Math.abs(floorChange)+(playerX+47))/4] + ":" + floorTracker[f.getFloorHeight(floorChange,playerX)]);
   
           if (screenChoice == 1)
         {
            int maxHP = 300;
            g.drawRect(0,0,300,48);
            int ratio = jeff.health/maxHP;
            int newX = ratio * 256;
            g.setColor(Color.GREEN);
            g.fillRect(0,0,newX,48);
         }
   
     
      
      // stuff to draw for game //
      
      g.dispose();
      g2d.dispose();
      bs.show();
   }
   
   private void stop() {
      if(!running) 
         return;
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
         } 
         else canRender = false;
         
         try {
            Thread.sleep(1);
         } 
         catch (InterruptedException e) {
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
      frame.setLocationRelativeTo(null);
      frame.setVisible(true); 
      frame.addWindowListener(
            new WindowAdapter() { 
               @Override public void windowClosing( WindowEvent e ) { e.getWindow().dispose(); } });
   
      
      
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
         frame.addWindowListener(
               new WindowAdapter() {
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
   public void keyTyped(KeyEvent e) {
   
   }


   public void keyPressed(KeyEvent e) {
      if(yvel == 0) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE){
            yvel = 98;
         }
      }
   }


   public void keyReleased(KeyEvent e) {
   
   }
    
    
   public void mouseClicked(MouseEvent e)
   {
      
   }
   public void mousePressed(MouseEvent e)
   {
      shoot = true;
   }
   public void mouseReleased(MouseEvent e)
   { 
      if(!running) {
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
