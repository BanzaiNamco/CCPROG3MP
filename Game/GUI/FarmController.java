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
                if(!plantRtCrop(0))
                    System.out.println("Crop is not root crop");
            }
        });
        view.setCarrotBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantRtCrop(1))
                    System.out.println("Crop is not root crop");
            }
        });
        view.setPotatoBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantRtCrop(2))
                    System.out.println("Crop is not root crop");
            }
        });
        view.setRoseBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantFlower(3))
                    System.out.println("Crop is not flower");
            }
        });
        view.setTulipsBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantFlower(4))
                    System.out.println("Crop is not flower");
            }
        });
        view.setSunflowerBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantFlower(5))
                    System.out.println("Crop is not flower");
            }
        });
        //TODO trees
        view.setPlowBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getUserInput();
                if(index != -1){
                    Tool plow = model.getTool(0);
                    Player player = model.getPlayer();
                    if(player.useTool(plow, model.getPlot(index))){
                        view.setFeedbackText("<html>Used  plow tool! <br/>Used " + plow.getUseCost() + " objectCoins!<br/>Got " + plow.getExpOnUse() + " exp!</html>");
                        updateDisplay();
                    }
                    else
                        view.setFeedbackText("<html>Cannot use tool there!</html>");
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
    private int getUserInput(){
        int index;
        String indexStr = JOptionPane.showInputDialog("Choose a tile");
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException a) {
            index = 0;
        }
        if (index > 0 && index < 51)
            return index-1;

        view.setFeedbackText("Invalid input!");
        return -1;
    }
    private boolean plantRtCrop(int i){
        int index = getUserInput();
        if(index != -1 && model.getCrop(i) instanceof RootCrop){
            RootCrop newCrop = (RootCrop) model.getCrop(i);
            Player player = model.getPlayer();
            if(player.plant(new RootCrop(newCrop), model.getPlot(index)))
                view.setFeedbackText("<html>Planted a " + newCrop.getName() + "<br/>Used " + newCrop.getCost() + " objectCoins!</html>");
            else
                view.setFeedbackText("<html>Cannot plant crop there!</html>");
            model.setPlayer(player);
            updateDisplay();
            return true;
        }
        return false;
    }
    private boolean plantFlower(int i){
        int index = getUserInput();
        if(index != -1 && model.getCrop(i) instanceof Flower){
            Flower newCrop = (Flower) model.getCrop(i);
            Player player = model.getPlayer();
            if(player.plant(new Flower(newCrop), model.getPlot(index)))
                view.setFeedbackText("<html>Planted a " + newCrop.getName() + "<br/>Used " + newCrop.getCost() + " objectCoins!</html>");
            else
                view.setFeedbackText("<html>Cannot plant crop there!</html>");
            model.setPlayer(player);
            updateDisplay();
            return true;
        }
        return false;
    }
}
