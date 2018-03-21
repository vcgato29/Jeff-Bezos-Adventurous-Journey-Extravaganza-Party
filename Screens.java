public class Screens
{
   int screenChoice;
   
   public Screens(int choice)
   {
      screenChoice = choice;
   }
   
   public void changeScreen(int newScreen)
   {
      screenChoice = newScreen;
   }
   
   public int getScreen()
   {
      return screenChoice;
   }
   
   public void display()
   {
      if(screenChoice == 0)
      {
      
      }
   }
}         
      