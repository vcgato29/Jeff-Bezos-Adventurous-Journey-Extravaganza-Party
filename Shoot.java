import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shoot {
   public int bulletX;
   public int bulletY;
   public int bulletSpeed=30; 
   
   private Texture bullet;
   
   public Shoot(Graphics2D g2d){
     bullet = new Texture("bullet");
   }
   
   public int fire(int inX, int inY, int screenWidth, Floor f, int floorChange, Graphics2D g2d) {
   
   int floorHeight;
   
      bulletX = inX + bulletSpeed;
      bulletY = inY;
      floorHeight = f.getFloorHeight(floorChange, bulletX);
      bullet.render(g2d,bulletX,bulletY);
      bulletSpeed += 30;
      //System.out.println("Bullet at " + bulletX);
      if (bulletX > screenWidth || bulletY >= floorHeight) {
         if (bulletX > screenWidth)
            System.out.println("Bullet exit screen: " + bulletY + "-" + floorHeight);
         else
            System.out.println("Bullet hit floor: " + bulletY + "-" + floorHeight);
         bulletSpeed = 30;
         bulletX = -1;
      }
      return bulletX;           
   }
   
   
}
   
   