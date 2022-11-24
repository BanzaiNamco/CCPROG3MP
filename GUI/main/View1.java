import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class View1{
    JFrame mainFrame;
    JPanel topPanel;
    JPanel leftPanel;
    JPanel bigPanel;
    JPanel gamePanel;
    ImagePanel imagePanel;
    JLayeredPane smallPanel;


    JLabel playerName = new JLabel();
    JLabel coins = new JLabel();
    JLabel day = new JLabel();
    JLabel level = new JLabel();
    JLabel exp = new JLabel();

    JButton turnipBtn = new JButton();
    JButton carrotBtn = new JButton();
    JButton potatoBtn = new JButton();
    JButton roseBtn = new JButton();
    JButton tulipsBtn = new JButton();
    JButton sunflowerBtn = new JButton();
    JButton mangoBtn = new JButton();
    JButton appleBtn = new JButton();

    JButton plowBtn = new JButton();
    JButton waterBtn = new JButton();
    JButton fertBtn = new JButton();
    JButton paxeBtn = new JButton();
    JButton shovelBtn = new JButton();
    

    public View1(){
        JOptionPane intro = new JOptionPane();
        mainFrame = new JFrame("MyFarm");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(925, 570));

        
        
        initPanels();
        mainFrame.setVisible(true);
    }
    public void start(){
        
    }
    private void initPanels(){
        bigPanel = new JPanel();
        topPanel = new JPanel(new GridLayout());
        leftPanel = new JPanel(new GridLayout(6, 6));
        imagePanel = new ImagePanel();
        gamePanel = new JPanel();
        smallPanel = new JLayeredPane();
        
        smallPanel.setLayout(null);
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBounds(0,0,925,570);
        
        smallPanel.setBackground(Color.black);
        imagePanel.setBackground(new Color(156,212,200));
        topPanel.setBackground(Color.decode("#DAB894"));
        leftPanel.setBackground(Color.decode("#DAB894"));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setBounds(48, 48, 100, 100);
        
        imagePanel.setPreferredSize(new Dimension(1000, 1000));
        gamePanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setPreferredSize(new Dimension(925, 150));
        leftPanel.setPreferredSize(new Dimension(200, 390));

        topPanel.add(playerName);
        topPanel.add(day);
        topPanel.add(coins);
        topPanel.add(level);
        topPanel.add(exp);

        turnipBtn.setText("Turnip");
        carrotBtn.setText("Carrot");
        potatoBtn.setText("Potato");
        tulipsBtn.setText("Tulips");
        sunflowerBtn.setText("Sunflower");
        mangoBtn.setText("Mango");
        appleBtn.setText("Apple");
        plowBtn.setText("Plow");
        waterBtn.setText("Water");
        fertBtn.setText("Fertilizer");
        paxeBtn.setText("Pickaxe");
        shovelBtn.setText("Shovel");


        leftPanel.add(turnipBtn);
        leftPanel.add(carrotBtn);
        leftPanel.add(potatoBtn);
        leftPanel.add(tulipsBtn);
        leftPanel.add(sunflowerBtn);
        leftPanel.add(mangoBtn);
        leftPanel.add(appleBtn);

        leftPanel.add(plowBtn);
        leftPanel.add(waterBtn);
        leftPanel.add(fertBtn);
        leftPanel.add(paxeBtn);
        leftPanel.add(shovelBtn);

        smallPanel.add(imagePanel, Integer.valueOf(1));
        smallPanel.add(gamePanel, Integer.valueOf(0));
        bigPanel.add(smallPanel, BorderLayout.CENTER);
        bigPanel.add(topPanel, BorderLayout.NORTH);
        bigPanel.add(leftPanel, BorderLayout.WEST);
        
        mainFrame.add(bigPanel);
    }




    public void setPlayerNameTxt(String txt){
        //TODO: Set font, size, arrange properly.
        playerName.setText(txt);
    }

    public void setDayTxt(String txt){
        day.setText(txt);
    }

    public void setCoinsTxt(String txt){
        coins.setText(txt);
    }

    public void setLevelTxt(String txt){
        level.setText(txt);
    }

    public void setExpTxt(String txt){
        exp.setText(txt);
    }
    
}
