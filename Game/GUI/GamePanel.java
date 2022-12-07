package GUI;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel{ //TODO FIND WITHERED ART
    private BufferedImage image;
    private ArrayList<BufferedImage> tileImg = new ArrayList<BufferedImage>();
    private int imageSize = 16;
    private int scale = 4;
    private int screenRows = 5;
    private int screenCols = 10;
    private int map[] = 
    {
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,11
    };


    public GamePanel() throws IOException{
        initImages();
    }
    
    //0 - reg
    //1 - seed
    //2 - rock
    //3 - h turnip
    //4 - h carrot
    //5 - harvestable potato
    //6 - h rose
    //7 - h tulip
    //8 - h sunflower
    //9 - apple tree
    //10 - mango h
    //11 - wither
    
    private void initImages() throws IOException{
        image = ImageIO.read(new File("GUI/www/img/tiles/grass_hill.png"));
        
        tileImg.add(image.getSubimage(0, 4 * imageSize, imageSize, imageSize));
        image = ImageIO.read(new File("GUI/www/img/objects/Basic Grass Biom things 1.png"));
        tileImg.add(image.getSubimage(8 * imageSize, imageSize, imageSize, imageSize));
        tileImg.add(image.getSubimage(5 * imageSize, imageSize, imageSize, imageSize));
        tileImg.add(ImageIO.read(new File("GUI/www/img/turnip.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/carrot.png")));
        tileImg.add(ImageIO.read(new File("GUI/www/img/potato.png")));
        tileImg.add(image.getSubimage(7 * imageSize, 3 * imageSize, imageSize, imageSize));
        tileImg.add(image.getSubimage(5 * imageSize, 3 * imageSize, imageSize, imageSize));
        tileImg.add(image.getSubimage(7 * imageSize, 2 * imageSize, imageSize, imageSize));
        tileImg.add(ImageIO.read(new File("GUI/www/img/Mango.png")));
        tileImg.add(image.getSubimage(3 * imageSize, 0, imageSize * 2, imageSize * 2));
        tileImg.add(ImageIO.read(new File("GUI/www/img/Dead.png")));

        
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

    public void changeMapTile(int index, int status){
        this.map[index] = status;
        repaint();
    }
}
