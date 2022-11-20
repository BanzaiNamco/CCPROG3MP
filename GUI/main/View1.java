import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class View1{
    JFrame mainFrame;
    JPanel topPanel;
    JPanel leftPanel;
    JLayeredPane bigPanel;
    ImagePanel imagePanel;

    public View1(){
        mainFrame = new JFrame("MyFarm");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(420,420));

        initPanels();
        mainFrame.setVisible(true);
    }
    private void initPanels(){
        bigPanel = new JLayeredPane();
        topPanel = new JPanel(new FlowLayout());
        leftPanel = new JPanel(new FlowLayout());
        imagePanel = new ImagePanel();
        

        bigPanel.setLayout(new BorderLayout());

        imagePanel.setBackground(new Color(156,212,200));

        
        topPanel.setPreferredSize(new Dimension(200,50));
        leftPanel.setPreferredSize(new Dimension(75,200));
        
        bigPanel.add(imagePanel);
        bigPanel.add(topPanel, BorderLayout.NORTH);
        bigPanel.add(leftPanel, BorderLayout.WEST);
        
        mainFrame.add(bigPanel);
    }

    
}
