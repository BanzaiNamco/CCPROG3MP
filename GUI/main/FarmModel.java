import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class FarmModel {
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    public FarmModel(){
        try{
            tiles.add(ImageIO.read(new File("../www/img/tiles/tilesets/grass.png")));
        } catch(IOException e){
            System.out.println("lol");
        }
    }

    public BufferedImage getTileImage(int i){
        return tiles.get(i);
    }
}
