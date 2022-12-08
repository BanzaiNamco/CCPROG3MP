package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class FarmView {
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
    private JLabel farmerType = new JLabel();
    private JLabel feedbackText = new JLabel();
    private JLabel selectedTileTxt = new JLabel();
    private JLabel levelUpTxt = new JLabel();

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
    
    private JButton plowBtn = new JButton();
    private JButton waterBtn = new JButton();
    private JButton fertBtn = new JButton();
    private JButton paxeBtn = new JButton();
    private JButton shovelBtn = new JButton();
    
    public FarmView(){
        mainFrame = new JFrame("MyFarm");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(990, 650));
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);    
        try {
            initPanels();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }
    public int endGame(){
        return JOptionPane.showConfirmDialog(null, "Game over. Start new game?");
    }
    public void closeGame(){
        mainFrame.dispose();
    }
    private void initPanels() throws IOException{
        bigPanel = new JPanel();
        topPanel = new JPanel(new GridLayout());
        miniTopPanel1 = new JPanel(new GridLayout(5, 2));
        miniTopPanel2 = new JPanel(new GridLayout(1, 2));
        rightPanel = new JPanel(new GridLayout(8, 1));
        leftPanel = new JPanel(new GridLayout(8, 1));
        imagePanel = new ImagePanel();
        gamePanel = new GamePanel();
        smallPanel = new JLayeredPane();
        
        gamePanel.setLayout(new GridLayout(5, 10));
        smallPanel.setLayout(null);
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBounds(0,0,925,570);
        
        setBackgrounds();

        imagePanel.setBounds(0,0, 775, 465);
        gamePanel.setBounds(64, 64, 640, 320);
        gamePanel.setOpaque(false);
        
        makeTextFancy();
        setPreferredSizes();
        setAllButtonTxt();
        addToTopPanel();
        addLeftPanelBtns();
        addRightPanelBtns();

        smallPanel.add(imagePanel, Integer.valueOf(0));
        smallPanel.add(gamePanel, Integer.valueOf(1));
        bigPanel.add(smallPanel, BorderLayout.CENTER);
        bigPanel.add(topPanel, BorderLayout.NORTH);
        bigPanel.add(leftPanel, BorderLayout.WEST);
        bigPanel.add(rightPanel, BorderLayout.EAST);
        
        mainFrame.add(bigPanel);
    }
    private void makeTextFancy(){
        playerName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        coins.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        day.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        level.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        exp.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        farmerType.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        feedbackText.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        selectedTileTxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
    }

    private void addToTopPanel(){
        miniTopPanel1.add(day);
        miniTopPanel1.add(coins);
        miniTopPanel1.add(playerName);
        miniTopPanel1.add(farmerType);
        miniTopPanel1.add(level);
        miniTopPanel1.add(exp);
        miniTopPanel1.add(levelUpTxt);
        miniTopPanel2.add(selectedTileTxt);
        miniTopPanel2.add(feedbackText);
        topPanel.add(miniTopPanel1);
        topPanel.add(miniTopPanel2);
    }
    private void setPreferredSizes(){
        gamePanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setPreferredSize(new Dimension(925, 150));
        leftPanel.setPreferredSize(new Dimension(100, 390));
        rightPanel.setPreferredSize(new Dimension(100, 390));
    }
    private void setBackgrounds(){
        imagePanel.setBackground(new Color(156,212,200));
        topPanel.setBackground(Color.decode("#DAB894"));
        leftPanel.setBackground(Color.decode("#DAB894"));
        rightPanel.setBackground(Color.decode("#DAB894"));
        miniTopPanel1.setBackground(Color.decode("#DAB894"));
        miniTopPanel2.setBackground(Color.decode("#DAB894"));
    }
    //dump for all btn.setText()
    private void setAllButtonTxt(){
        farmerRegistration.setText("Register");
        advanceDay.setText("Sleep");
        turnipBtn.setText("Turnip");
        carrotBtn.setText("Carrot");
        potatoBtn.setText("Potato");
        roseBtn.setText("Rose");
        tulipsBtn.setText("Tulip");
        sunflowerBtn.setText("Sunflower");
        mangoBtn.setText("Mango");
        appleBtn.setText("Apple");
        plowBtn.setText("Plow");
        waterBtn.setText("Water");
        fertBtn.setText("Fertilizer");
        paxeBtn.setText("Pickaxe");
        shovelBtn.setText("Shovel");
        harvestBtn.setText("Harvest");
    }
    //add all components to leftPanel
    private void addLeftPanelBtns(){
        leftPanel.add(turnipBtn);
        leftPanel.add(carrotBtn);
        leftPanel.add(potatoBtn);
        leftPanel.add(roseBtn);
        leftPanel.add(tulipsBtn);
        leftPanel.add(sunflowerBtn);
        leftPanel.add(mangoBtn);
        leftPanel.add(appleBtn);
    }
    //add all relevant components to rightPanel
    private void addRightPanelBtns(){
        rightPanel.add(plowBtn);
        rightPanel.add(waterBtn);
        rightPanel.add(fertBtn);
        rightPanel.add(paxeBtn);
        rightPanel.add(shovelBtn);
        rightPanel.add(harvestBtn);
        rightPanel.add(advanceDay);
        rightPanel.add(farmerRegistration);
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
    public void setPlayerNameTxt(String txt){
        playerName.setText(txt);
    }
    public void setLevelUpTxt(String s){
        levelUpTxt.setText(s);
    }
    public void setDayTxt(int day2){
        day.setText("Day " + day2);
    }
    public void setCoinsTxt(float d){
        coins.setText(d + " objectCoins");
    }
    public void setLevelTxt(int i){
        level.setText("Level " + i);
    }
    public void setExpTxt(float d){
        exp.setText(d + " exp");
    }
    public void setSelectedTileTxt(String s){
        selectedTileTxt.setText(s);
    }
    public void setFarmerTypeTxt(String s){
        farmerType.setText(s);
    }
    public void changeTileImg(int row, int col, boolean plowed){
        imagePanel.update(row, col, plowed);
    }
    public void changeGameTile(int index, int status){
        gamePanel.changeMapTile(index, status);
    }
    public void setGamePanelMouseListener(MouseListener e){
        gamePanel.addMouseListener(e);
    }
}
