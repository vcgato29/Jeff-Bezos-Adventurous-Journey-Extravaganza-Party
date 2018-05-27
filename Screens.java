
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
   
   private static final String TITLE = "JEff";
   private static final int WIDTH = 1280;
   private static final int HEIGHT = 960;
   public static int screenChoice;
   private static JFrame frame;
   private JPanel PlayPanel;
   private JPanel InstructionPanel;
   private BufferStrategy bs;
   private Graphics g;
   private Graphics2D g2d;
   private Texture texture;
   private Texture background;
   private static Screens INSTANCE; 
   private int mouseX= MouseInfo.getPointerInfo().getLocation().x;
   private int mouseY= MouseInfo.getPointerInfo().getLocation().y;

   // configuration variables
   private int gravity = 2;
   private int maxJumpHeight = 150;
   private int playerPosition = 93;
   private int floorBase = 600;
   private int floorDamage = 100; // damage caused by hitting floor
   private int mapLength = 3200;
   private int stepSize = 4;
   private int enemyCount = 3;

   // local variables
   private int brickWidth = 32;
   private int brickHeight = 32;
   private boolean shoot = false;
   private int x = 0;
   private int y;
   private int count = 0;
   private int jumpHeight = 0;
   private int floorChange = 0;
   private int bulletX = 0;
   private int bulletY = 0;
   private boolean running;

   // objects
   private int [] floorMap = new int[mapLength/stepSize];     
   private Floor f;
   private Shoot shooter;     
   private Player jeff;
   private Enemy [] enemies = new Enemy[enemyCount];
   

         
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
      for(int i = 0; i < floorMap.length; i++)
      {
         floorMap[i] = 0;
      }

   }

   private void createScene(){
   
      bs = getBufferStrategy();
      if (bs == null) {
           createBufferStrategy(3);
            bs = getBufferStrategy();
      }
      g = bs.getDrawGraphics();
      g2d = (Graphics2D) g;
      g2d.translate(-6, -28);

      f = new Floor(0, mapLength, stepSize, floorBase, floorMap, g2d);
      floorMap = createMap(floorMap,brickWidth,stepSize,20,50,1); //blocks from 20 to 50 level 1
      floorMap = createMap(floorMap,brickWidth,stepSize,35,45,2);
      floorMap = createMap(floorMap,brickWidth,stepSize,80,100,1);
      f.floorMap = floorMap;
      
      shooter = new Shoot(g2d);
      jeff = new Player(playerPosition, floorBase, f, g2d);
      
      enemies[0] = new Enemy(f,jeff,400,0,1,"apple logo goomba static",g2d); //enemy at 400 pixels
      enemies[1] = new Enemy(f,jeff,1200,1,3,"apple logo goomba static",g2d);
      enemies[2] = new Enemy(f,jeff,2500,0,3,"apple logo goomba static",g2d);

   }      
   
   private int[] createMap(int[] fM, int width, int stepValue, int start, int end, int height)
   {
      for(int i = start; i < end; i++)
      {
         fM[i] = height;
      }
      return fM;
   }

   
   private void render(BufferStrategy bs, Graphics g, Graphics2D g2d) {
      
      ///////////////////////////
      
      mouseX= MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
      mouseY= MouseInfo.getPointerInfo().getLocation().y - frame.getLocation().y; 

      g2d.setColor(Color.RED);
      g2d.fillRect(0,0,WIDTH,HEIGHT);
      background.render(g2d,0,0);
      texture.render(g2d,mouseX-125,mouseY-25);

      // move floor
      f.move(floorChange, g2d);
      
      // move enemies
      for (int i=0;i<enemyCount;i++){
         enemies[i].move(floorChange, stepSize, g2d);
      }
      
      // move player, store jump height to avoid double jumps
      jumpHeight = jeff.move(jumpHeight, gravity, floorChange, floorDamage, g2d);
        
      // check if shooting
      if (shoot) {
         bulletX = shooter.fire(bulletX,bulletY,WIDTH,f,floorChange,g2d);
         if (bulletX == -1){ // bullet left screen or hit wall
            shoot = false;
         }
         else { // check if we're hitting an enemy
            for (int i=0;i<enemyCount;i++){
               if (enemies[i].isAlive==true){
                  if (enemies[i].checkHit(shooter))
                  {
                     //System.out.println("Kill " + i);   
                     enemies[i].kill();
                     shoot = false;
                  }
               }
            }
         }
      }
      else {
         bulletX = mouseX -115; // behind the gun
         bulletY = mouseY - 25;
      }

      // check if game is over
      if (f.getFloorHeight(floorChange, jeff.playerX)<0 || jeff.health==0)
         running = false;

      // move the floor
      floorChange -= stepSize;
   
      // show health
      if (screenChoice == 1)
         {
            int maxHP = 300;
            g.drawRect(0,0,maxHP,48);
            int newX = jeff.health;
            g.setColor(Color.GREEN);
            g.fillRect(0,0,newX,48);
         }
      
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
   
      createScene();
         
      while(running) {
         long now = System.nanoTime();
         unprocessed += (now - lastTime) / nsPerTick;
         lastTime = now;
         
         if(unprocessed >= 1.0) {
            
            unprocessed--;
            tps++;
            canRender = true;
         } 
         else {
            canRender = false;
         }
         
         try {
            Thread.sleep(1);
         } 
         catch (InterruptedException e) {
            e.printStackTrace();
         }
         
         if(canRender) {
            render(bs, g, g2d);
            
            fps++;
            
         }
         
         if(System.currentTimeMillis() - 1000 > timer) {
            timer += 1000;
            //System.out.printf("FPS: %d | TPS: %d\n",fps,tps);
            fps = 0;
            tps = 0;
         }
      
      }
      System.out.println("Level Finished");
  
      g.dispose();
      g2d.dispose();
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
      if(jumpHeight == 0) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE){
            jumpHeight = maxJumpHeight;
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
