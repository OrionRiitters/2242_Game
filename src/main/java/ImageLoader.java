import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageLoader {
                                        // This will hold all images
    private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();


    protected BufferedImage getImage(String s) {
        return images.get(s);
    }

    private BufferedImage loadImage(File f) {  // Returns BufferedImage found from File f
        BufferedImage img;
        try {
            img = ImageIO.read(f);
            return img;
        } catch (IOException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    protected void addImageToHashMap(String s, File f) {  // Adds image from File f to images ArrayList
        try {
            BufferedImage newImage = loadImage(f);
            images.put(s, newImage);
        } catch (NullPointerException exc) {exc.printStackTrace();}
    }

}
