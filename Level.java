import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Level
{
   public int [] floorMap; 
   public Enemy [] enemies;
   public int enemyCount = 0;

   public Level(int levelNr, int mapLength, int stepSize, int maxEnemyCount)
   {
      floorMap = new int[mapLength/stepSize];     
      enemies = new Enemy[maxEnemyCount];
      for(int i = 0; i < floorMap.length; i++)
      {
         floorMap[i] = 0;
      }

   }

   public void createMap(int start, int end, int height)
   {
      for(int i = start; i < end; i++)
      {
         floorMap[i] = height;
      }
   }

   public void createEnemy(Floor f, int eX, int eSpeed, int eType, Player eP, String eImage, Graphics2D g2d)
   {
      enemies[enemyCount] = new Enemy(f,eP,eX,eSpeed,eType,eImage,g2d); 
      enemyCount++;
   }   

}
