package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import farm.*;
import seeds.*;

public class FarmController {
    private FarmModel model;
    private View1 view;
    private int selectedTile = -1;
    
    public FarmController(FarmModel model, View1 view){
        this.view = view;
        this.model = model;
        
        view.setPlayerNameTxt(model.getPlayer().getName());
        updateDisplay();

        setButtons();
        
    }

    private void updateDisplay(){
        view.setLevelUpTxt(null);
        if(model.playerUpdate())
            view.setLevelUpTxt("Level up!");
        updateTileDisplayTxt();
        updateTileImg();
        Player player = model.getPlayer();
        view.setCoinsTxt(player.getObjectCoins());
        view.setDayTxt(model.getDay());
        view.setLevelTxt(player.getLevel());
        view.setExpTxt(player.getExp());

        if(player instanceof LegendaryFarmer)
            view.setFarmerTypeTxt("Legendary Farmer");
        else if (player instanceof DistinguishedFarmer)
            view.setFarmerTypeTxt("Distinguished Farmer");
        else if(player instanceof RegisteredFarmer)
            view.setFarmerTypeTxt("Registered Farmer");
        else
            view.setFarmerTypeTxt("Farmer");
        
    }
    private void updateTileImg(){
        int plot[] = model.getPlotMap();
        for(int i = 0; i < plot.length; i++){
            view.changeTileImg((int)Math.floor(i/10), i%10, model.getPlot(i).getPlowed());
        }
        for(int i = 0; i < plot.length; i++){
            view.getGamePanel().changeMapTile(i, plot[i]);
        }
    }
    private void updateTileDisplayTxt(){
        if(selectedTile != -1){
            if(model.getPlot(selectedTile).getCrop() != null){
                Tile tile = model.getPlot(selectedTile);
                int selected = selectedTile+1;
                
                if(!model.getPlot(selectedTile).IsPlantDead())
                    view.setSelectedTileTxt("<html>Tile " + selected + "<br/>" + tile.getCrop().getName() + "<br/>Times watered: " + tile.getTimesWatered() +
                        "<br/>Times fertilized: " + tile.getTimesFertilized() + "<br/>Days until harvest: " + tile.getTimeTilHarvest());
                else if(model.getPlot(selectedTile).IsPlantDead())
                    view.setSelectedTileTxt("<html>Tile " + selected + "<br/>" + tile.getCrop().getName() + " is dead.</html>");

            }
            else{
                Tile tile = model.getPlot(selectedTile);
                int selected = selectedTile + 1;
                
                view.setSelectedTileTxt("<html>Tile " + selected + "<br/>No plant <br/>Plow status: " + tile.getPlowed() + "<br/>Rock status: " + tile.getRock());
            }
        }
        else{
            view.setSelectedTileTxt("<html>No tile selected!</html>");
        }
    }
    
    private void setButtons(){
        view.setTurnipBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(0);
            }
        });
        view.setCarrotBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(1);
            }
        });
        view.setPotatoBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(2);
            }
        });
        view.setRoseBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(3);
            }
        });
        view.setTulipsBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(4);
            }
        });
        view.setSunflowerBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(5);
            }
        });
        view.setMangoBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(6);
            }
        });
        view.setAppleBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                plantSeed(7);
            }
        });
        //TODO trees
        view.setPlowBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                useTool(0);
                }
        });

        view.setWaterBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                useTool(1);
            }
        });

        view.setFertBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                useTool(2);
            }
        });

        view.setPaxeBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                useTool(3);
            }
        });

        view.setShovelBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                double exp = model.getPlayer().getExp();
                String display = "<html>Please select a tile!</html>";
                if(selectedTile != -1){
                    if(model.useTool(selectedTile, 4)){
                            display = "<html>Used  shovel! <br/>Used " + model.getTool(4).getUseCost() + " objectCoins!";
                        if(exp != model.getPlayer().getExp())
                            display += "<br/>Got " + model.getTool(4).getExpOnUse() + " exp!</html>";
                    }
                }
                view.setFeedbackText(display);     
                updateDisplay();               
            }
        });

        view.setFarmerRegistrationActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                double coins = model.getPlayer().getObjectCoins();
                if(model.upgradePlayer()){
                    coins -= model.getPlayer().getObjectCoins();
                    view.setFeedbackText("<html>Upgrade complete! <br/>Used " + coins + " object coins!</html>");
                }
                else
                    view.setFeedbackText("Upgrade failed!");
                updateDisplay();
            }
        });
        view.setAdvanceDayActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!model.advanceDay()){
                    view.endGame();
                }
                else
                    view.setFeedbackText("Advanced to next day!");
                updateDisplay();
            }

        });

        view.setHarvestBtn(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedTile != -1){
                    int prod = model.getPlot(selectedTile).getNumOfProduce();
                    Crop crop = model.getPlot(selectedTile).getCrop();
                    double harvestTotal = model.getHarvestTotal(selectedTile);
                    if(model.harvest(selectedTile))
                        view.setFeedbackText("<html>Sold " + prod + " " 
                            +  crop.getName() + "s!.<br/>Got " + crop.getExpYield() + " exp!<br/> Got " + harvestTotal + " object coins!</html>");
                }
                selectedTile = -1;
                updateDisplay();
            }

        });
        view.setGamePanelMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedTileCol = (int) Math.ceil(e.getX()/64);
                int selectedTileRow = (int) Math.ceil(e.getY()/64);
                selectedTile = selectedTileRow * 10 + selectedTileCol;
                updateDisplay();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int selectedTileCol = (int) Math.ceil(e.getX()/64);
                int selectedTileRow = (int) Math.ceil(e.getY()/64);
                selectedTile = selectedTileRow * 10 + selectedTileCol;
                updateDisplay();
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            });
    }

    private void useTool(int i){
        String display = "<html>Please select a tile!</html>";
        if(selectedTile != -1){
            if(model.useTool(selectedTile, i)){
                int selected = selectedTile+1;
                display = "<html>Used  " + model.getTool(i).getName() + " on Tile " + selected + "! <br/>Used " + model.getTool(i).getUseCost() +
                                    " objectCoins!<br/>Got " + model.getTool(i).getExpOnUse() + " exp!</html>";
            }
            else
                display = "<html>Cannot use" + model.getTool(i).getName() + " there!</html>";
        }
        view.setFeedbackText(display);
        updateDisplay();
    }
    private void plantSeed(int i){
        String display = "<html>Please select a tile!</html>";
        if(selectedTile != -1){
            double coins = model.getPlayer().getObjectCoins();
            if(model.plant(selectedTile, i)){
                Crop crop = model.getCrop(i);
                coins -= model.getPlayer().getObjectCoins();
                display = "<html>Planted a " + crop.getName() + "!<br/>Used " + coins + " object coins!</html>";
                updateDisplay();
            }
            else{
                display = "<html>Could not plant seed!</html>";
            }
        }
        view.setFeedbackText(display);
        updateDisplay();
    }
}
