package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import farm.*;
import seeds.*;
import tools.*; 

public class FarmController {
    private FarmModel model;
    private View1 view;
    private int day = 1;
    
    public FarmController(FarmModel model, View1 view){
        this.view = view;
        this.model = model;
        
        view.setPlayerNameTxt(model.getPlayer().getName());
        updateDisplay();

        setButtons();
        
    }

    private void updateDisplay(){
        view.setCoinsTxt(model.getPlayer().getObjectCoins());
        view.setDayTxt(day);
        view.setLevelTxt(model.getPlayer().getLevel());
        view.setExpTxt(model.getPlayer().getExp());
    }
    
    private void setButtons(){
        view.setTurnipBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int index;
                String indexStr = JOptionPane.showInputDialog("Choose a tile");
                try {
                    index = Integer.parseInt(indexStr);
                } catch (NumberFormatException a) {
                    index = 0;
                }
                if (index <= 0 || index > 50)
                    view.setFeedbackText("Invalid input!");
                else if(index > 0 && index < 51){
                    index--;
                    model.getPlot(index).plant(new RootCrop((RootCrop) model.getCrop(0))); //TODO REPEAT FOR ALL BUTTONS
                }
            }

        });

        view.setFarmerRegistrationActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Player temp = model.getPlayer();
                // if(temp instanceof Upgradeable)
                //     temp = Upgradeable.upgrade(temp);
                // else{
                //     view.setFeedbackText("<html>Unable to upgrade</html>");
                // } //TODO try this again, need to be able to set feedback text when upgrade fails
                if(temp instanceof Farmer){
                    if(temp.getLevel() >= RegisteredFarmer.getLevelReq() && temp.getObjectCoins() >= RegisteredFarmer.getCost()){
                        temp = new RegisteredFarmer(temp);
                        model.setPlayer(temp);
                        System.out.println(model.getPlayer() instanceof RegisteredFarmer);
                        updateDisplay();
                    }
                    else{
                        int lvlReq = RegisteredFarmer.getLevelReq() - temp.getLevel();  
                        double moneyReq = RegisteredFarmer.getCost() - temp.getObjectCoins();
                        view.setFeedbackText("<html>Unable to upgrade to Registered Farmer.<br/><br/>You need "
                                             + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                    }
                }
                else if (temp instanceof RegisteredFarmer){
                    if(temp.getLevel() >= DistinguishedFarmer.getLevelReq() && temp.getObjectCoins() >= DistinguishedFarmer.getCost()){
                        temp = new LegendaryFarmer(temp);
                        model.setPlayer(temp);
                        updateDisplay();
                    }
                    else{
                        int lvlReq = DistinguishedFarmer.getLevelReq() - temp.getLevel();  
                        double moneyReq = DistinguishedFarmer.getCost() - temp.getObjectCoins();
                        view.setFeedbackText("<html>Unable to upgrade to Distinguished Farmer.<br/><br/>You need "
                                             + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                    }
                    
                }
                else if(temp instanceof DistinguishedFarmer){
                    if(temp.getLevel() >= LegendaryFarmer.getLevelReq() && temp.getObjectCoins() >= LegendaryFarmer.getCost()){
                        temp = new LegendaryFarmer(temp);
                        model.setPlayer(temp);
                        updateDisplay();
                    }
                    else{
                        int lvlReq = LegendaryFarmer.getLevelReq() - temp.getLevel();  
                        double moneyReq = LegendaryFarmer.getCost() - temp.getObjectCoins();
                        view.setFeedbackText("<html>Unable to upgrade to Legendary Farmer.<br/><br/>You need "
                                             + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                    }
                }
                else{
                    view.setFeedbackText("<html>Max upgrade already reached!</html>");
                }
                
            }
        });
        view.setAdvanceDayActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                model.getPlayer().update();
                for(int i = 0; i < model.getPlotSize(); i++){
                    Crop temp = model.getPlot(i).getCrop();
                    if(temp != null){
                        temp.update();
                    }
                }
                day++;
                updateDisplay();
                if(!model.checkStatus()){
                    JFrame end = new JFrame();
                    //TODO end the game
                }
            }

        });

        view.setAdminPriv(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                model.getPlayer().addExp();
                if(model.getPlayer().update())
                    view.setFeedbackText("<html>Level UP!</html>");
                updateDisplay();
            }

        });
    }
    
}
