package GUI;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImagePanel extends JPanel{
    private BufferedImage image;
    private ArrayList<BufferedImage> ground = new ArrayList<BufferedImage>();
    private int imageSize = 16;
    private int scale = 4;
    private int screenRows = 7;
    private int screenCols = 12;
    private int rows = 7;
    private int cols = 11;
    private int map[][] = {
        {0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 14},
        {1 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 15},
        {1 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 15},
        {1 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 15},
        {1 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 15},
        {1 ,8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 15},
        {2, 9, 9, 9 ,9 ,9 ,9 ,9 ,9 ,9, 9, 16},
    };
    

    public ImagePanel() throws IOException{
        image = ImageIO.read(new File("GUI/www/img/tiles/grass_hill.png"));
        

        initImages();
    }    

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        super.paint(g2d);

        for(int i = 0; i < screenCols; i ++){
            for (int j = 0; j < screenRows; j++){
                g2d.drawImage(ground.get(map[j][i]), i * imageSize * scale, j * imageSize * scale, imageSize * scale, imageSize * scale,  this);
            }
        }
        g2d.dispose();
    }

    private void initImages(){

        for(int i = 0 ; i < cols; i++){
            for (int j = 0; j < rows; j++){
                ground.add(image.getSubimage(i * imageSize, j * imageSize, imageSize, imageSize));
            }        
        }
    }

    //Create draw() method
    //add a setter/changer for the center pieces OR
    //Create another class for the center pieces
    //create another another class for the seeds
}
