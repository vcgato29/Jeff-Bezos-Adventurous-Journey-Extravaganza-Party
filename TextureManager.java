import java.awt.image.*;

public class TextureManager extends ResourceManager {
   public BufferedImage image;
   
   public TextureManager(BufferedImage image){
      this.image = image;
   }
   
   
   
   public BufferedImage getImage() {
      return image;
   }
   
}