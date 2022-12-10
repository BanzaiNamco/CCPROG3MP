//Orrin Landon T. Uy ID12111287
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import farm.*;
import seeds.*;
/**
 * This class is the controller for the GUI
 */
public class FarmController {
    private FarmModel model;
    private FarmView view;
    private int selectedTile = -1;
    
    /**
     * Creates a new FarmController object.
     * @param model {@link GUI.FarmModel} object that will be used for the game.
     * @param view {@link GUI.FarmView} object that will be used for the game.
     */
    public FarmController(FarmModel model, FarmView view){
        this.view = view;
        this.model = model;
        
        view.setPlayerNameTxt(model.getPlayer().getName());
        updateDisplay();

        setButtons();
        setButtonTxt();
    }
    private void setButtonTxt(){
        int costList[] = new int[8];
        int toolCostList[] = new int[5];
        for (int i = 0; i < 8; i++){
            costList[i] = (int) model.getCrop(i).getCost();
        }
        for(int i = 0; i < 5; i++){
            toolCostList[i] = (int) model.getTool(i).getUseCost();
        }
        view.setAllButtonTxt(costList, toolCostList, ((Upgradeable)model.getPlayer()).getLevelNeed(), ((Upgradeable)model.getPlayer()).getObjectCoinNeed());
    }
    /**
     * This method updates all text displays on screen.
     */
    private void updateDisplay(){
        view.setFarmerRegistrationBtnTxt(((Upgradeable)model.getPlayer()).getLevelNeed(), ((Upgradeable)model.getPlayer()).getObjectCoinNeed());
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

    /**
     * This method updates all the images in {@link GUI.FarmView} based on the status of the game from {@link GUI.FarmModel}.
     */
    private void updateTileImg(){
        int plot[] = model.getPlotMap();
        for(int i = 0; i < plot.length; i++){
            view.changeTileImg((int)Math.floor(i/10), i%10, model.getPlot(i).getPlowed());
        }
        for(int i = 0; i < plot.length; i++){
            view.getGamePanel().changeMapTile(i, plot[i]);
        }
    }

    /**
     * This method updates the display text for the selected tile.
     */
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
    
    /**
     * This method sets all the buttons in {@link GUI.FarmView}.
     */
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
                if(model.getPlayer().getObjectCoins() <= 5)
                    endGame();
            }
        });

        view.setShovelBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                double exp = model.getPlayer().getExp();
                String display = "<html>Please select a tile!</html>";
                if(selectedTile != -1){
                    if(model.useTool(selectedTile, 4)){
                            int tile = selectedTile+1;
                            display = "<html>Used  shovel on tile " + tile + "!<br/>Used " + model.getTool(4).getUseCost() + " objectCoins!";
                        if(exp != model.getPlayer().getExp())
                            display += "<br/>Got " + model.getTool(4).getExpOnUse() + " exp!</html>";
                    }
                    else
                        display = "<html>Not enough object coins to use shovel!</html>";
                }
                view.setFeedbackText(display);     
                updateDisplay();              
                
                if(model.getPlayer().getObjectCoins() < 5 && !model.checkStatus())
                    endGame();
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

                if(model.getPlayer().getObjectCoins() < 5 && !model.checkStatus())
                    endGame();
            }
        });
        view.setAdvanceDayActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!model.advanceDay()){
                    endGame();
                }
                else
                    view.setFeedbackText("Advanced to next day!");
                selectedTile = -1;
                updateDisplay();
            }

        });

        view.setHarvestBtn(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedTile != -1){
                    int prod = model.getPlot(selectedTile).getNumOfProduce();
                    Crop crop = model.getPlot(selectedTile).getCrop();
                    if(crop != null){
                        double harvestTotal = model.getHarvestTotal(selectedTile);
                        if(model.harvest(selectedTile))
                            view.setFeedbackText("<html>Sold " + prod + " " 
                                +  crop.getName() + "s!.<br/>Got " + crop.getExpYield() + " exp!<br/> Got " + harvestTotal + " object coins!</html>");
                        else
                            view.setFeedbackText("Harvesting failed!");
                    }
                    else
                        view.setFeedbackText("Harvesting failed!");
                }
                else
                    view.setFeedbackText("Please select a tile!");
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
            public void mousePressed(MouseEvent e) { //not necessary but makes the game more responsive to clicks
                int selectedTileCol = (int) Math.ceil(e.getX()/64);
                int selectedTileRow = (int) Math.ceil(e.getY()/64);
                selectedTile = selectedTileRow * 10 + selectedTileCol;
                updateDisplay();
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            });
    }

    private void endGame(){
        updateDisplay();
            if(view.endGame() == 0){
                try{
                    String name = model.getPlayer().getName();
                    model = new FarmModel(name);
                }
                catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }
                view.closeGame();
                view = new FarmView();
                view.setPlayerNameTxt(model.getPlayer().getName());
                selectedTile = -1;
                view.setFeedbackText(null);
                setButtons();
                setButtonTxt();
                updateDisplay();
            }
            else{
                view.closeGame();
            }
    }
    /**
     * This method calls {@link GUI.FarmModel#useTool(int, int)} if a tile has been selected and updates the display text accordingly.
     * @param i
     */
    private void useTool(int i){
        String display = "<html>Please select a tile!</html>";
        if(selectedTile != -1){
            if(model.useTool(selectedTile, i)){
                int selected = selectedTile+1;
                display = "<html>Used  " + model.getTool(i).getName() + " on Tile " + selected + "! <br/>Used " + model.getTool(i).getUseCost() +
                                    " objectCoins!<br/>Got " + model.getTool(i).getExpOnUse() + " exp!</html>";
            }
            else if(model.getPlayer().getObjectCoins() < model.getTool(i).getUseCost())
                display = "<html>Not enough object coins to use " + model.getTool(i).getName() + "!</html>";
            else
                display = "<html>Cannot use " + model.getTool(i).getName() + " there!</html>";
        }
        view.setFeedbackText(display);
        updateDisplay();
    }

    /**
     * This method calls {@link GUI.FarmModel#plant(int, int)} if a tile is selected and updates the display accordingly.
     * @param i
     */
    private void plantSeed(int i){
        String display = "<html>Please select a tile!</html>";
        if(selectedTile != -1){
            double coins = model.getPlayer().getObjectCoins();
            if(model.plant(selectedTile, i)){
                Crop crop = model.getCrop(i);
                coins -= model.getPlayer().getObjectCoins();
                int tile = selectedTile+1;
                display = "<html>Planted a " + crop.getName() + " on tile " + tile + "!<br/>Used " + coins + " object coins!</html>";
                updateDisplay();
            }
            else if(model.getPlayer().getObjectCoins() < model.getCrop(i).getCost())
                display = "<html>Not enough object coins to plant " + model.getCrop(i).getName() + "!</html>";
            else{
                display = "<html>Could not plant seed!</html>";
            }
        }
        view.setFeedbackText(display);
        updateDisplay();
    }
}
