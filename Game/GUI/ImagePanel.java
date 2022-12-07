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
    private int map[][] = {
        {0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 14},
        {1 ,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15},
        {1 ,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15},
        {1 ,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15},
        {1 ,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15},
        {1 ,5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 15},
        {2, 9, 9, 9 ,9 ,9 ,9 ,9 ,9 ,9, 9, 16},
    };
    

    public ImagePanel() throws IOException{
        
        this.setDoubleBuffered(true);
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

    private void initImages() throws IOException{
        image = ImageIO.read(new File("GUI/www/img/tiles/grass_hill.png"));
        for(int i = 0 ; i < 3; i++){
            for (int j = 0; j < rows; j++){
                ground.add(image.getSubimage(i * imageSize, j * imageSize, imageSize, imageSize));
            }        
        }
        image = ImageIO.read(new File("GUI/www/img/tiles/Tilled Dirt.png"));
        ground.add(image.getSubimage(0, 0, imageSize, imageSize));
    }

    //used to change the tile from grass to plot or otherwise
    public void update(int row, int col, boolean plowed){
        row++;
        col++;
        if(plowed)
            map[row][col] = 21;
        else
            map[row][col] = 5;

        repaint();
    }
}
