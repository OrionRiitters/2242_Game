import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageLoader {
                                        // This will hold all images
    private static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

    public static BufferedImage getImage(int i) {
        return images.get(i);
    }


    public static BufferedImage loadImage(File f) {  // Returns BufferedImage found from File f
        BufferedImage img;
        try {
            img = ImageIO.read(f);
            return img;
        } catch (IOException exc) {
            exc.printStackTrace();
            return null;
        }
    }

    public static void addImageToArray(File f) {  // Adds image from File f to images ArrayList
        try {
            BufferedImage newImage = loadImage(f);
            images.add(newImage);
        } catch (NullPointerException exc) {exc.printStackTrace();}
    }

}
