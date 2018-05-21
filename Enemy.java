public class Enemy
{
   public int health; 
   public int damage; 
   public int enemyX;
   public int enemyY;
   public int type;
   public Texture t = new Texture("apple logo goomba static");

   public Enemy (int	type)	
   {
      if	(type	==	1)
      {
         health =	100;
         damage =	50;
      }
      else if(type == 2)
      {
         health =	150;
         damage =	50;
      }
      else if (type == 3)
      {
         health =	200;
         damage =	50;
      }
      else if (type == 4)
      {
         health =	250;
         damage =	100;
      }
      else if (type == 5)
      {
         health =	300;
         damage =	100;
      }
   }
}