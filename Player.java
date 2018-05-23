public class Player
{
   public int health = 300;
   public int gunSelection;
   boolean isAlive = true; 
   public int x ;
   public int y ; 
   public int armX = x + 47;
   public int armY = y + 33;
   public Texture jeffy;
   public Texture jeffyArm;
   
   public Player() {}

   public int getPlayerHeight()
   {
      return (int)((y + 72) /32);
   }

}

