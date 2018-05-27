import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player
{
   public int health = 300;
   public int gunSelection;
   boolean isAlive = true; 
   public int gravMag = 0;
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

   public void move(int jump, int gravity, int floorChange, Graphics2D g2d){
   
      int floorHeight;
      
      // see if we're jumping
      if (jump > 0) {
         gravMag -= (gravity*gravity);
         jump = jump + gravMag;  
      }         

      // bring player back to floor level slowly if necessary
      if (jump > 0){ // check right side of player
         playerY = playerY - jump;
         floorHeight = f.floorBase - height - f.getFloorHeight(floorChange,playerX);
      }
      else {// check left side of player
         floorHeight = f.floorBase - height - f.getFloorHeight(floorChange,playerX);
      }

      if (playerY < floorHeight){
         playerY += (gravity*gravity);
         hit = false;
      }
      armY = playerY + 26;

      // check collision with floor
      if (playerY > floorHeight + 2 && hit != true){
         health = health - 100;
         hit = true;
      }      
      
      jeffy.render(g2d,playerX,playerY);
      jeffyArm.render(g2d,armX,armY);

   }

   public int getPlayerHeight()
   {
      return 20 - (int)((playerY + height) /32);
   }
  

}

