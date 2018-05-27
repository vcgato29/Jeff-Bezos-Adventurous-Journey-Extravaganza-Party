import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy
{
   public int health; 
   public int damage; 
   public int enemyX;
   private int width;
   private int height;
   public int enemyY;
   public int enemyType;
   boolean isAlive;
   public int enemySpeed;
   private Floor f;
   private Player p;
   private boolean hit = false;
   public Texture image;   
   public boolean backwards = false;

   public Enemy (Floor eF, Player eP, int eX, int eSpeed, int type, String sImage, Graphics2D g2d)	
   {
      f = eF;
      p = eP;
      image = new Texture(sImage);
      width = image.getWidth();
      height = image.getHeight();
      enemyX = eX;
      enemyY = f.floorBase - f.getFloorHeight(0, enemyX) - height;
      enemySpeed = eSpeed;
      enemyType = type;
      if	(enemyType	==	1)
      {
         health =	100;
         damage =	50;
      }
      else if(enemyType == 2)
      {
         health =	150;
         damage =	50;
      }
      else if (enemyType == 3)
      {
         health =	200;
         damage =	50;
      }
      else if (enemyType == 4)
      {
         health =	250;
         damage =	100;
      }
      else if (enemyType == 5)
      {
         health =	300;
         damage =	100;
      }
      image.render(g2d, enemyX, enemyY);
   }
   
   public void move(int floorChange, int stepSize, Graphics2D g2d)
   {
      enemyX = enemyX - stepSize - enemySpeed;
      if (enemyX >=0) {
         enemyY = f.floorBase - f.getFloorHeight(floorChange, enemyX) - height ;
         image.render(g2d, enemyX, enemyY);
         
         // check player collision
         if (p.playerX + p.width > enemyX && p.playerX < enemyX + width && p.playerY + p.height >= enemyY && hit != true){
            //System.out.println(p.width + "-" + width); 
            p.health = p.health - 100;
            hit = true;
         }
      }
   }
   
   public void turnAround(Enemy e, int floorChange, Graphics2D g2d)
   {
   }
}