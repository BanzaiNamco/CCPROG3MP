package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class View1 {
    private JFrame mainFrame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bigPanel;
    private GamePanel gamePanel;
    private ImagePanel imagePanel;
    private JLayeredPane smallPanel;
    private JPanel miniTopPanel1;
    private JPanel miniTopPanel2;

    private JLabel playerName = new JLabel();
    private JLabel coins = new JLabel();
    private JLabel day = new JLabel();
    private JLabel level = new JLabel();
    private JLabel exp = new JLabel();
    private JLabel feedbackText = new JLabel();

    private JButton advanceDay = new JButton();
    private JButton farmerRegistration = new JButton();
    private JButton turnipBtn = new JButton();
    private JButton carrotBtn = new JButton();
    private JButton potatoBtn = new JButton();
    private JButton roseBtn = new JButton();
    private JButton tulipsBtn = new JButton();
    private JButton sunflowerBtn = new JButton();
    private JButton mangoBtn = new JButton();
    private JButton appleBtn = new JButton();
    private JButton harvestBtn = new JButton();
    private JButton checkTileBtn = new JButton();
    
    private JButton plowBtn = new JButton();
    private JButton waterBtn = new JButton();
    private JButton fertBtn = new JButton();
    private JButton paxeBtn = new JButton();
    private JButton shovelBtn = new JButton();
    
    public View1(){
        mainFrame = new JFrame("MyFarm");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(990, 650));
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);    
        try {
            initPanels();
        } catch (IOException e) {
            // TODO ENDGAME
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }
    public void start(){
        
    }
    private void initPanels() throws IOException{
        bigPanel = new JPanel();
        topPanel = new JPanel(new GridLayout());
        miniTopPanel1 = new JPanel();
        miniTopPanel2 = new JPanel();
        rightPanel = new JPanel(new GridLayout(8, 1));
        leftPanel = new JPanel(new GridLayout(8, 1));
        imagePanel = new ImagePanel();
        gamePanel = new GamePanel();
        //gamePanel2 = new JPanel(); //TODO maybe make this into ANOTHER view? NOTE: this is for the seeds/plant layer
        //TODO add a harvest button
        smallPanel = new JLayeredPane();
        
        gamePanel.setLayout(new GridLayout(5, 10));
        smallPanel.setLayout(null);
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBounds(0,0,925,570);
        
        imagePanel.setBackground(new Color(156,212,200));
        topPanel.setBackground(Color.decode("#DAB894"));
        leftPanel.setBackground(Color.decode("#DAB894"));
        rightPanel.setBackground(Color.decode("#DAB894"));
        miniTopPanel1.setBackground(Color.decode("#DAB894"));
        miniTopPanel2.setBackground(Color.decode("#DAB894"));

        imagePanel.setBounds(0,0, 775, 465);
        gamePanel.setBounds(64, 64, 640, 320);
        gamePanel.setOpaque(false);
        
        gamePanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setPreferredSize(new Dimension(925, 150));
        leftPanel.setPreferredSize(new Dimension(100, 390));
        rightPanel.setPreferredSize(new Dimension(100, 390));

        farmerRegistration.setText("Register");
        advanceDay.setText("Sleep");
        checkTileBtn.setText("Check Tile");
        miniTopPanel1.add(playerName);
        miniTopPanel1.add(day);
        miniTopPanel1.add(coins);
        miniTopPanel1.add(level);
        miniTopPanel1.add(exp);
        miniTopPanel1.add(checkTileBtn);
        miniTopPanel2.add(feedbackText);
        topPanel.add(miniTopPanel1);
        topPanel.add(miniTopPanel2);

        turnipBtn.setText("Turnip");
        carrotBtn.setText("Carrot");
        potatoBtn.setText("Potato");
        roseBtn.setText("Rose");
        tulipsBtn.setText("Tulips");
        sunflowerBtn.setText("Sunflower");
        mangoBtn.setText("Mango");
        appleBtn.setText("Apple");
        plowBtn.setText("Plow");
        waterBtn.setText("Water");
        fertBtn.setText("Fertilizer");
        paxeBtn.setText("Pickaxe");
        shovelBtn.setText("Shovel");
        harvestBtn.setText("Harvest");


        leftPanel.add(turnipBtn);
        leftPanel.add(carrotBtn);
        leftPanel.add(potatoBtn);
        leftPanel.add(roseBtn);
        leftPanel.add(tulipsBtn);
        leftPanel.add(sunflowerBtn);
        leftPanel.add(mangoBtn);
        leftPanel.add(appleBtn);

        rightPanel.add(plowBtn);
        rightPanel.add(waterBtn);
        rightPanel.add(fertBtn);
        rightPanel.add(paxeBtn);
        rightPanel.add(shovelBtn);
        rightPanel.add(harvestBtn);
        rightPanel.add(advanceDay);
        rightPanel.add(farmerRegistration);

        

        smallPanel.add(imagePanel, Integer.valueOf(0));
        smallPanel.add(gamePanel, Integer.valueOf(1));
        bigPanel.add(smallPanel, BorderLayout.CENTER);
        bigPanel.add(topPanel, BorderLayout.NORTH);
        bigPanel.add(leftPanel, BorderLayout.WEST);
        bigPanel.add(rightPanel, BorderLayout.EAST);
        
        mainFrame.add(bigPanel);
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }
    
    public void setFeedbackText(String a){
        feedbackText.setText(a);
    }

    public void setHarvestBtn(ActionListener e){
        harvestBtn.addActionListener(e);
    }
    
    public void setTurnipBtnActionListener(ActionListener e){
        turnipBtn.addActionListener(e);
    }
    
    public void setCarrotBtnActionListener(ActionListener e){
        carrotBtn.addActionListener(e);
    }

    public void setPotatoBtnActionListener(ActionListener e){
        potatoBtn.addActionListener(e);
    }

    public void setRoseBtnActionListener(ActionListener e){
        roseBtn.addActionListener(e);
    }

    public void setTulipsBtnActionListener(ActionListener e){
        tulipsBtn.addActionListener(e);
    }

    public void setSunflowerBtnActionListener(ActionListener e){
        sunflowerBtn.addActionListener(e);
    }

    public void setMangoBtnActionListener(ActionListener e){
        mangoBtn.addActionListener(e);
    }

    public void setAppleBtnActionListener(ActionListener e){
        appleBtn.addActionListener(e);
    }

    public void setPlowBtnActionListener(ActionListener e){
        plowBtn.addActionListener(e);
    }

    public void setWaterBtnActionListener(ActionListener e){
        waterBtn.addActionListener(e);
    }

    public void setFertBtnActionListener(ActionListener e){
        fertBtn.addActionListener(e);
    }

    public void setPaxeBtnActionListener(ActionListener e){
        paxeBtn.addActionListener(e);
    }

    public void setShovelBtnActionListener(ActionListener e){
        shovelBtn.addActionListener(e);
    }

    public void setFarmerRegistrationActionListener(ActionListener e){
        farmerRegistration.addActionListener(e);
    }

    public void setAdvanceDayActionListener(ActionListener e){
        advanceDay.addActionListener(e);
    }

    public void setCheckTileBtnActionListener(ActionListener e){
        checkTileBtn.addActionListener(e);
    }
    
    public void setPlayerNameTxt(String txt){
        //TODO: Set font, size, arrange properly.
        playerName.setText(txt);
    }

    public void setDayTxt(int day2){
        day.setText("Day " + day2);
    }

    public void setCoinsTxt(double d){
        coins.setText(d + " objectCoins");
    }

    public void setLevelTxt(int i){
        level.setText("Level " + i);
    }

    public void setExpTxt(double d){
        exp.setText(d + " exp");
    }
    
    public void changeTileImg(int row, int col, boolean plowed){
        imagePanel.update(row, col, plowed);
    }

    public void setGamePanelMouseListener(MouseListener e){
        gamePanel.addMouseListener(e);
    }
}
