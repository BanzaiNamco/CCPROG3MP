package GUI;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel{
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
        0,0,0,0,0,0,0,0,0,0
    };


    public GamePanel() throws IOException{
        
        initImages();
    }
    
    
    private void initImages() throws IOException{
        image = ImageIO.read(new File("GUI/www/img/tiles/grass_hill.png"));
        
        tileImg.add(image.getSubimage(0, 5 * imageSize, imageSize, imageSize));
        image = ImageIO.read(new File("GUI/www/img/tiles/Tilled Dirt.png"));
        tileImg.add(image.getSubimage(0, 0, imageSize, imageSize));
        
        //change image

    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        super.paint(g2d);

        for(int i = 0; i < screenCols; i ++){
            for (int j = 0; j < screenRows; j++){
                g2d.drawImage(tileImg.get(map[j * screenRows + i]), i * imageSize * scale, j * imageSize * scale, imageSize * scale, imageSize * scale,  this);
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
}
