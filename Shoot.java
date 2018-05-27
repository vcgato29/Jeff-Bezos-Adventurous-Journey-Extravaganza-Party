import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shoot {
   private int count=0;
   public int bulletX;
   public int bulletY;
   public int bulletSpeed=30;
   
   
   private Texture bullet = new Texture("bullet");
   
   public Shoot(Graphics2D g2d){
     
   }
   
   public int fire(int inX, int inY, int screenWidth, Floor f, int floorChange, Graphics2D g2d) {
   
   int floorHeight;
   
      bulletX = inX + bulletSpeed;
      bulletY = inY;
      floorHeight = f.getFloorHeight(0, bulletX);
      count++;
      bullet.render(g2d,bulletX,bulletY);
      bulletSpeed += 30;
      if (count > 42 || bulletX > screenWidth || bulletY >= floorHeight) {
         count = 0;
         bulletSpeed = 30;
         bulletX = -1;
      }
      return bulletX;           
   }
   
   
}
   
   