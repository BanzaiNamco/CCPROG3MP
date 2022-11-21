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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class View1{
    JFrame mainFrame;
    JPanel topPanel;
    JPanel leftPanel;
    JLayeredPane bigPanel;
    ImagePanel imagePanel;

    JLabel playerName = new JLabel();
    JLabel coins = new JLabel();
    JLabel day = new JLabel();
    JLabel level = new JLabel();
    JLabel exp = new JLabel();
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
        bigPanel = new JLayeredPane();
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel = new JPanel(new FlowLayout());
        imagePanel = new ImagePanel();
        

        bigPanel.setLayout(new BorderLayout());

        imagePanel.setBackground(new Color(156,212,200));
        topPanel.setBackground(Color.decode("#DAB894"));
        leftPanel.setBackground(Color.decode("#DAB894"));

        
        topPanel.setPreferredSize(new Dimension(200,150));
        leftPanel.setPreferredSize(new Dimension(200,200));

        topPanel.add(playerName);
        topPanel.add(day);
        topPanel.add(coins);
        topPanel.add(level);
        topPanel.add(exp);
        
        bigPanel.add(imagePanel);
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
