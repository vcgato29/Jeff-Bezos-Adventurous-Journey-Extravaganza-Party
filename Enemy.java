import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Enemy
{
   public int health; 
   public int damage;
   public int bulletDamage = 50;
   public int armor = 0; 
   public int enemyX;
   private int width;
   private int height;
   public String imgName;
   public int enemyY;
   public int enemyType;
   public boolean isAlive = true;
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
      imgName = sImage;
      image = new Texture(sImage);
      width = image.getWidth();
      height = image.getHeight();
      enemyX = eX;
      enemyY = f.getFloorHeight(0, enemyX);
      enemySpeed = eSpeed;
      enemyType = type;
      if	(enemyType	==	1)
      {
         health =	100;
         damage =	50;
         armor = 10;
      }
      else if(enemyType == 2)
      {
         health =	150;
         damage =	50;
         armor = 10;
      }
      else if (enemyType == 3)
      {
         health =	200;
         damage =	50;
         armor = 10;
      }
      else if (enemyType == 4)
      {
         health =	250;
         damage =	100;
         armor = 10;        
      }
      else if (enemyType == 5)
      {
         health =	300;
         damage =	100;
         armor = 10;         
      }
      image.render(g2d, enemyX, enemyY);
   }
   
   public void move(int floorChange, int stepSize, int screenWidth, Graphics2D g2d)
   {
      if (isAlive==true){
         if (backwards==false)
            enemyX = enemyX - stepSize - enemySpeed;
         else
            enemyX = enemyX + stepSize + enemySpeed;
   
         if (enemyX >= screenWidth && backwards==true){        
            turnAround();
         }
         if (enemyX >=0) { // check only when in play
            enemyY = f.getFloorHeight(floorChange, enemyX-width) - height ;
            image.render(g2d, enemyX, enemyY);
            
            // check player collision
            if (p.playerX + p.width > enemyX && p.playerX < enemyX + width && p.playerY + p.height >= enemyY && hit != true){
               p.health = p.health - damage;
               System.out.println("You hit enemy: your health=" + p.health);
               hit = true;
            }
         }
      }
   }
   
   public void kill()
   {
      isAlive=false;
      System.out.println("Killed");
   }
   
   public boolean checkHit(Shoot shooter)
   {
      if (shooter.bulletX - shooter.bulletSpeed <= enemyX + width && shooter.bulletY >= enemyY && shooter.bulletY <= enemyY + height)
      { // there is a chance for a hit
//         if (shooter.bulletX > enemyX && shooter.bulletX < enemyX + width && )
  //       {
            health = health - bulletDamage + armor;
            System.out.println("Enemy Hit : enemy health=" + health + " (armor=" + armor + ")");
            if (health <= 0){
               kill();
            }
            else {   
               turnAround();
            }
            return true;            
    //     }
      }
      else {
         return false;
//         System.out.println("Miss: bspeed=" + shooter.bulletSpeed + ";bx=" + shooter.bulletX + ";lem=" + enemyX + ";rem=" + (int)(enemyX + width) + ";by=" + shooter.bulletY + "tem=" + enemyY + ";bem=" + (int)(enemyY + height));
         
      }
         
   }
   // makes the enemy turn around
   public void turnAround()
   {
      if (backwards==false)
         backwards = true;
      else
         backwards = false;
   }
}