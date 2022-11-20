import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FarmView extends JFrame{
    private JButton[] btns;
    private JPanel tilePanel;

    public FarmView(){
        super("MyFarm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 400);
        
        initPanel();
        add(tilePanel, BorderLayout.NORTH);
        setVisible(true);
    }
    private void initPanel(){
        tilePanel = new JPanel();
        tilePanel.setBackground(Color.black);
        ImageIcon img = new ImageIcon("../www/img/tiles/upper_left.png");
        JButton btn = new JButton(img);
        btn.setContentAreaFilled(false);
        btn.setBorder(null);
        btn.addActionListener(
            System.out.println("Helelo");
        );
        tilePanel.add(btn, BorderLayout.WEST);
    }
}

/*public class FarmView extends JFrame{
    final int tileSize = 16;
    final int scale = 5;
    final int newTileSize = tileSize * scale;
    final int maxCols = 12;
    final int maxRows = 8;
    final int height = maxRows * newTileSize;
    final int width = maxCols * newTileSize;
    private BufferedImage sprites[] = new BufferedImage[8*8];

    public FarmView(){
        super("MyFarm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setSize(width, height);
        init();

        setVisible(true);
    }

    private void init(){
        try{createMapSpriteList(tileSize, tileSize, tileSize);}
        catch (IOException e) {
            System.out.println("IO Exception");
        };

        //bot panel

    }


    private void drawMap(Graphics2D graph){
        
    }*/
