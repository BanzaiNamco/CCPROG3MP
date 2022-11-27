package GUI;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

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
        view.setFarmerRegistrationActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Player temp = model.getPlayer();
                if(temp instanceof Farmer){
                    if(temp.getLevel() >= RegisteredFarmer.getLevelReq() && temp.getObjectCoins() >= RegisteredFarmer.getCost()){
                        temp = new RegisteredFarmer(temp);
                        model.setPlayer(temp);
                    }
                    else{
                        System.out.println("Requirements not met"); //TODO add to gui and add to others
                    }
                }
                else if (temp instanceof RegisteredFarmer){
                    if(temp.getLevel() >= DistinguishedFarmer.getLevelReq() && temp.getObjectCoins() >= DistinguishedFarmer.getCost()){
                        temp = new LegendaryFarmer(temp);
                        model.setPlayer(temp);
                    }
                }
                else if(temp instanceof DistinguishedFarmer){
                    if(temp.getLevel() >= LegendaryFarmer.getLevelReq() && temp.getObjectCoins() >= LegendaryFarmer.getCost()){
                        temp = new LegendaryFarmer(temp);
                        model.setPlayer(temp);
                    }
                }
                //TODO add notif if cannot upgrade anymore
                //maybe add a display as well
                
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
    }
    
}
