package GUI;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel{
    private BufferedImage image;
    private ArrayList<BufferedImage> tileImg = new ArrayList<BufferedImage>();
    private int imageSize = 16;
    private int scale = 4;
    private int screenRows = 5;
    private int screenCols = 10;
    private int map[] = 
    {
        2,2,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,2,3,4,5,6,7,8,9,10,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0
    };


    public GamePanel() throws IOException{
        initImages();
    }
    
    //0 - reg
    //1 - plowed
    //2 - rock
    //3 - seed
    //4 - harvestable turnip
    //5 - harvestable carrot
    //6 - harvestable potato
    //7 - h rose
    //8 - h tulip
    //9 - h sunflower
    
    private void initImages() throws IOException{
        image = ImageIO.read(new File("GUI/www/img/tiles/grass_hill.png"));
        
        tileImg.add(image.getSubimage(0, 4 * imageSize, imageSize, imageSize));
        image = ImageIO.read(new File("GUI/www/img/tiles/Tilled Dirt.png"));
        tileImg.add(image.getSubimage(0, 0, imageSize, imageSize));
        
        tileImg.add(ImageIO.read(new File("GUI/www/img/rock.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/turnip.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/carrot.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/potato.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/potato.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/flower_harvest_pink.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/flower_harvest_pink.png"))); //TODO TULIPS CHANGE COLOR
        tileImg.add(ImageIO.read(new File("GUI/www/img/flower_harvest.png")));
        image = ImageIO.read(new File("GUI/www/img/objects/Basic tools and meterials.png"));//TODO DELETE
        tileImg.add(image.getSubimage(0, 0, imageSize, imageSize));
        
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        super.paint(g2d);

        for(int i = 0; i < screenCols; i ++){
            for (int j = 0; j < screenRows; j++){
                g2d.drawImage(tileImg.get(map[j *10 + i]), i * imageSize * scale, j * imageSize * scale, imageSize * scale, imageSize * scale,  this);
            }
        }
        g2d.dispose();
    }

    /*
     * Not plowed = 1
     * plowed = 0
     */
    public void setTileImage(int index, boolean plowed){
        if(plowed)
            map[index] = 0;
        else
            map[index] = 1;
        
    }

    public void changeMapTile(int index, int status){
        this.map[index] = status;
        repaint();
    }
}
