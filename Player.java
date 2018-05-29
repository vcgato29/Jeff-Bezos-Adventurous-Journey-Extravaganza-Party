import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player
{
   public int health = 300;
   public int gunSelection;
   boolean isAlive = true; 
   public int gravMag = 0;
   public int jumpValue;
   public int playerX ;
   public int playerY ; 
   public int width = 50;
   public int height = 72;
   public int armX;
   public int armY;
   private boolean hit = false;
   private Floor f;
   public Texture jeffy;
   public Texture jeffyArm;
   
   public Player(int eX, int eY, Floor eF, Graphics2D g2d) {
      f = eF;
      playerX = eX;
      playerY= eY - height;
      armX = playerX + 45;
      armY = playerY + 26;
      jeffy = new Texture("bezos");
      jeffyArm = new Texture("bezosarm");
      jeffyArm.render(g2d, armX, armY);
      jeffy.render(g2d, playerX, playerY);
   }

   public int move(int jump, int gravity, int floorChange, int floorDamage, Graphics2D g2d){
   
      int floorHeight;
           
      // check if we're jumping, do not allow double jump
      if (jump > 0 && jumpValue==0){ 
         playerY = playerY - jump;
         jumpValue = jump;
      }

      // bring player back to floor level slowly if necessary     
      floorHeight = f.getFloorHeight(floorChange,playerX);

      if (playerY + height < floorHeight){ 
         playerY += (gravity*gravity);
         jumpValue -= (gravity*gravity);
         hit = false;
      }
      else {
         jumpValue = 0;
      }
      armY = playerY + 26; // reset arm

      // check collision with floor
      if (playerX - floorChange + width > (f.mapLength * 8) - 10){
         jumpValue = -1;
      }
      else 
         if (playerY + height > floorHeight + 2 && hit != true){
            health = health - floorDamage;
            System.out.println("You hit floor: health=" + health + " : " + (int)(playerX - floorChange) + " : " + (int)(f.mapLength * 8));
            hit = true;
         }      
      
      jeffy.render(g2d,playerX,playerY);
      jeffyArm.render(g2d,armX,armY);
      
      return jumpValue;

   } 

}

