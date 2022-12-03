package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import farm.*;
import seeds.*;
import tools.*; 

public class FarmController {
    private FarmModel model;
    private View1 view;
    private int day = 1;
    private int selectedTileRow = -1;
    private int selectedTileCol = -1;
    
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
        view.setMangoBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantTree(6))
                    System.out.println("Crop is not tree");
            }
        });
        view.setAppleBtnActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!plantTree(6))
                    System.out.println("Crop is not tree");
            }
        });
        //TODO trees
        view.setPlowBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Tool plow = model.getTool(0);
                int index = useTool(plow);
                if(index != -1){     
                    index++;
                    view.setFeedbackText("<html>Used  plow tool on tile " + index + "! <br/>Used " + plow.getUseCost() + " objectCoins!<br/>Got " + plow.getExpOnUse() + " exp!</html>");
                    index--;
                    view.changeTileImg(index/10, index%10 , true);
                }
                else
                    view.setFeedbackText("<html>Cannot use plow tool there!</html>");
            }
        });

        view.setWaterBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Tool water = model.getTool(1);
                int index = useTool(water);
                if(index != -1)
                    view.setFeedbackText("<html>Used  watering can! <br/>Used " + water.getUseCost() + " objectCoins!<br/>Got " + water.getExpOnUse() + " exp!</html>");
                else
                    view.setFeedbackText("<html>Cannot use watering can there!</html>");
            }
        });

        view.setFertBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Tool fert = model.getTool(2);
                int index = useTool(fert);
                if(index != -1)
                    view.setFeedbackText("<html>Used  fertilizer! <br/>Used " + fert.getUseCost() + " objectCoins!<br/>Got " + fert.getExpOnUse() + " exp!</html>");
                else
                    view.setFeedbackText("<html>Cannot use fertilizer there!</html>");
            }
        });

        view.setPaxeBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Tool pickaxe = model.getTool(3);
                int index = useTool(pickaxe);
                if(index != -1)
                    view.setFeedbackText("<html>Used  pickaxe! <br/>Used " + pickaxe.getUseCost() + " objectCoins!<br/>Got " + pickaxe.getExpOnUse() + " exp!</html>");
                else
                    view.setFeedbackText("<html>Cannot use pickaxe there!</html>");
            }
        });

        view.setShovelBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                double exp = model.getPlayer().getExp();
                Tool shovel = model.getTool(4);
                int index = useTool(shovel);
                if(index != -1)
                    if(exp < model.getPlayer().getExp())//TODO something is wrong
                        view.setFeedbackText("<html>Used  shovel! <br/>Used " + shovel.getUseCost() + " objectCoins!</html>");
                    else
                        view.setFeedbackText("<html>Used  shovel! <br/>Used " + shovel.getUseCost() + " objectCoins!<br/>Got " + shovel.getExpOnUse() + " exp!</html>");
                else
                    view.setFeedbackText("<html>Cannot use shovel there!</html>");
            }
        });

        view.setFarmerRegistrationActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Player temp = model.getPlayer();
                if(temp instanceof Upgradeable){
                     temp = ((Upgradeable) temp).upgrade2();
                     if(temp == null){
                         temp = model.getPlayer();
                         view.setFeedbackText("<html>Unable to upgrade. <br/><br/> You need " + ((Upgradeable) temp).getLevelNeed() + 
                            " more levels<br/>and " + ((Upgradeable) temp).getObjectCoinNeed() + " more objectCoins");
                     } //TODO try this again, need to be able to set feedback text when upgrade fails
                }
                // if(temp instanceof Farmer){
                //     if(temp.getLevel() >= RegisteredFarmer.getLevelReq() && temp.getObjectCoins() >= RegisteredFarmer.getCost()){
                //         temp = new RegisteredFarmer(temp);
                //         model.setPlayer(temp);
                //         updateDisplay();
                //     }
                //     else{
                //         int lvlReq = RegisteredFarmer.getLevelReq() - temp.getLevel();  
                //         double moneyReq = RegisteredFarmer.getCost() - temp.getObjectCoins();
                //         view.setFeedbackText("<html>Unable to upgrade to Registered Farmer.<br/><br/>You need "
                //                              + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                //     }
                // }
                // else if (temp instanceof RegisteredFarmer){
                //     if(temp.getLevel() >= DistinguishedFarmer.getLevelReq() && temp.getObjectCoins() >= DistinguishedFarmer.getCost()){
                //         temp = new LegendaryFarmer(temp);
                //         model.setPlayer(temp);
                //         updateDisplay();
                //     }
                //     else{
                //         int lvlReq = DistinguishedFarmer.getLevelReq() - temp.getLevel();  
                //         double moneyReq = DistinguishedFarmer.getCost() - temp.getObjectCoins();
                //         view.setFeedbackText("<html>Unable to upgrade to Distinguished Farmer.<br/><br/>You need "
                //                              + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                //     }
                    
                // }
                // else if(temp instanceof DistinguishedFarmer){
                //     if(temp.getLevel() >= LegendaryFarmer.getLevelReq() && temp.getObjectCoins() >= LegendaryFarmer.getCost()){
                //         temp = new LegendaryFarmer(temp);
                //         model.setPlayer(temp);
                //         updateDisplay();
                //     }
                //     else{
                //         int lvlReq = LegendaryFarmer.getLevelReq() - temp.getLevel();  
                //         double moneyReq = LegendaryFarmer.getCost() - temp.getObjectCoins();
                //         view.setFeedbackText("<html>Unable to upgrade to Legendary Farmer.<br/><br/>You need "
                //                              + lvlReq + " more levels<br/>and " + moneyReq + " more objectCoins</html>");
                //     }
                // }
                else{
                    view.setFeedbackText("<html>Max upgrade already reached!</html>");
                }
                
            }
        });
        view.setAdvanceDayActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                model.getPlayer().update();
                Crop temp;
                for(int i = 0; i < model.getPlotSize(); i++){
                    temp = model.getPlot(i).getCrop();
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

        view.setHarvestBtn(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getUserInput();
                if(index != -1){
                    Tile tile = model.getPlot(index);
                    Crop crop = model.getPlot(index).getCrop();
                    if(model.getPlayer().harvestCrop(tile))
                        view.setFeedbackText("Harvested " + crop.getName());
                }
                updateDisplay();
            }

        });
        view.setCheckTileBtnActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getUserInput();
                if(index != -1){
                    if(model.getPlot(index).getCrop() != null){
                        Crop crop = model.getPlot(index).getCrop();
                        index++;
                        view.setFeedbackText("<html>Tile " + index + "<br/>" + crop.getName() + "<br/>Times watered: " + crop.getTimesWatered() +
                            "<br/>Times fertilized: " + crop.getTimesFertilized() + "<br/>Days until harvest: " + crop.getHarvestTime());
                    }
                    else{
                        Tile tile = model.getPlot(index);
                        index++;
                        view.setFeedbackText("<html>Tile " + index + "<br/>No plant <br/>Plow status: " + tile.getPlowed() + "<br/>Rock status: " + tile.getRock());
                    }
                }
         }
        });
        view.setGamePanelMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                selectedTileCol = (int) Math.ceil(e.getX()/64);
                selectedTileRow = (int) Math.ceil(e.getY()/64);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
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
    private int getUserInput(){
        int index;
        String indexStr = JOptionPane.showInputDialog("Choose a tile");
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException a) {
            index = -1;
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
                view.setFeedbackText("<html>Cannot plant crop!</html>");
            
            model.setPlayer(player);
            updateDisplay();
            return true;
        }
        return false;
    }
    private boolean plantTree(int i){
        int index = getUserInput();
        if(index > 9 && index < 40 && model.getCrop(i) instanceof Tree){ //checks if the index points to the top or bottom row
            int tile = (index+1) % 10; 
            if(tile != 0 && tile != 1){ //checks if the index points to a tile on the right or left side 
                Tree tree = (Tree) model.getCrop(i);
                Player player = model.getPlayer();
                if(player.plant(new Tree(tree), model.getPlot(index))){
                    view.setFeedbackText("<html>Planted a " + tree.getName() + "<br/>Used " + tree.getCost() + " objectCoins!</html>");
                    model.setPlayer(player);
                }
            }
        }
        else
            view.setFeedbackText("<html>Cannot plant tree there!</html>");
        updateDisplay();
        return true;
    }
    private int useTool(Tool tool){
        int index = getUserInput();
        if(index != -1){
            Player player = model.getPlayer();
            if(player.useTool(tool, model.getPlot(index))){
                updateDisplay();
                return index;
            }
        }
        return -1;
    }
}
