package GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import farm.*;
import seeds.*;
import tools.*;

/**
 * This class is the Model for the MyFarm game.
 * <p>
 * This class functions as the state of the game class.
 */
public class FarmModel {
    private Player player;
    private ArrayList<Tile> plot;
    private ArrayList<Crop> seedList;
    private ArrayList<Tool> toolList;
    private int day = 1;
    private int[] plotMap;

    /**
     * Constructor that creates a new FarmModel object.
     * <p>
     * All variables are initialized here.
     * @param name name of the player.
     * @throws FileNotFoundException when the files containing vital game object stats are missing.
     */
    public FarmModel(String name) throws FileNotFoundException{
        player = new Farmer(name);
        this.plot = new ArrayList<Tile>();
        this.seedList = new ArrayList<Crop>();
        this.toolList = new ArrayList<Tool>();
        initPlotMap();
        for (int i = 0; i < 50; i++){
            plot.add(new Tile(plotMap[i]));
        }
        seedsInit();
        toolsInit();
    }

    /**
     * This method updates the entirety of {@link GUI.FarmModel#plot} and increments the day counter.
     * <p>
     * This method calls {@link GUI.FarmModel#checkStatus()} and stores it into the variable status.
     * before doing any updating.
     * @return status.
     */
    public boolean advanceDay(){
        for(int i = 0; i < plot.size(); i++){
            plot.get(i).update();
            updatePlotMap();
        }
        boolean status = checkStatus(); //check end of the day status put this at the top of the method to allow 1 day allowance b4 game end
        day++;
        return status;
    }

    /**
     * This method calls {@link farm.Player#update()}.
     * @return {@link farm.Player#update()}.
     */
    public boolean playerUpdate(){
        return player.update();
    }

    /**
     * This method harvests the plant in the specified tile.
     * <p>
     * This method also calls {@link farm.Player#gainExp(double)}, {@link farm.Player#addObjectCoins(double)} and
     * {@link farm.Tile#resetTile()} if the harvest is successful.
     * 
     * @param tileIndex
     * @return
     */
    public boolean harvest(int tileIndex){
        if(plot.get(tileIndex).getCrop() != null){ //if plant is in tile
            if(!plot.get(tileIndex).IsPlantDead() && plot.get(tileIndex).getTimeTilHarvest() == 0){ //plant is on harvest day and not dead <---important
                double harvestTotal = getHarvestTotal(tileIndex);
                player.gainExp(plot.get(tileIndex).getCrop().getExpYield()); //add to player stats
                player.addObjectCoins(harvestTotal);
                plot.get(tileIndex).resetTile(); //reset the tile
                updatePlotMap(); //update plot map
                return true;
            }
        }
        return false;
    }

    /**
     * This method calculates the final harvest total of the tile/crop being harvested.
     * <p>
     * This method makes use of {@link GUI.FarmModel#getWaterBonus(double, Tile)} and {@link GUI.FarmModel#getFertBonus(double, Tile)}
     * @param tileIndex is the {@link farm.Tile} with the {@link seeds.Crop} that is to be harvested
     * @return harvestTotal, the final calculated value of a {@link seeds.Crop}.
     */
    public double getHarvestTotal(int tileIndex){
        double harvestTotal = (plot.get(tileIndex).getNumOfProduce()) //initial harvest Total value
                                * (plot.get(tileIndex).getCrop().getBaseSellingPrice() + ((Farmer)player).getBonusEarn());
        double waterBonus = getWaterBonus(harvestTotal, plot.get(tileIndex));
        double fertBonus = getFertBonus(harvestTotal, plot.get(tileIndex));
        harvestTotal += waterBonus + fertBonus;
        if(plot.get(tileIndex).getCrop() instanceof Flower)
            harvestTotal *= 1.1;
        
        harvestTotal = Math.round(harvestTotal*100.0) / 100.0; //this rounds the final value to 2 decimals (rounds up)
        return harvestTotal;
    }

    /**
     * This method calculates the water bonus for the crop's harvest total.
     * <p>
     * Limit checking of the times the tile was watered is also done here.
     * @param harvestTotal initial harvestTotal value without the Water and Fertilizer Bonus.
     * @param tile {@link farm.Tile} object with the {@link seeds.Crop} object that will be harvested.
     * @return final calculated Water Bonus to be added into harvestTotal.
     */
    private double getWaterBonus(double harvestTotal, Tile tile){
        if(tile.getTimesWatered() > tile.getCrop().getWaterLimit() + ((Farmer)player).getBonusWater()) //compare timesWatered and Limit here (player bonus included)
            return harvestTotal * 0.2 * (tile.getCrop().getWaterLimit() + ((Farmer)player).getBonusWater() - 1);
        return harvestTotal * 0.2 * (tile.getTimesWatered()-1);
    }
    /**
     * This method calculates the fertilizer bonus for the crop's harvest total.
     * <p>
     * Limit checking of the times the tile was fertilized is also done here.
     * @param harvestTotal initial harvestTotal value without the Water and Fertilizer Bonus.
     * @param tile {@link farm.Tile} object with the {@link seeds.Crop} object that will be harvested.
     * @return final calculated Fertilizer Bonus to be added into harvestTotal.
     */
    private double getFertBonus(double harvestTotal, Tile tile){
        if(tile.getTimesFertilized() > tile.getCrop().getFertilizerLimit() + ((Farmer)player).getBonusFert()) //compare timesFertilized and limit here(player bonus included)
            return harvestTotal * 0.5 * (tile.getCrop().getFertilizerLimit() + ((Farmer)player).getBonusFert());
        return harvestTotal * 0.5 * tile.getTimesFertilized();
    }

    /**
     * This method gets a {@link seeds.Crop} from {@link GUI.FarmModel#seedList} and plants it into the
     * specified {@link farm.Tile} in {@link GUI.FarmModel#plot}.
     * <p>
     * This method makes use of {@link GUI.FarmModel#arePlotSidesEmpty(int)} and {@link farm.Player#plant(Crop, Tile)}.
     * Planting fails if the user tries to plant a {@link seeds.Tree} into the sides and corners of the plot.
     * 
     * @param plotIndex the index of the tile in {@link GUI.FarmModel#plot} where the {@link seeds.Crop} object will be planted to.
     * @param seedIndex index of the seed in {@link GUI.FarmModel#seedList} that will be planted.
     * @return false if planting failed, true otherwise.
     */
    public boolean plant(int plotIndex, int seedIndex){
        if(seedList.get(seedIndex) instanceof Tree){
            if(!arePlotSidesEmpty(plotIndex) || (plotIndex%10 == 0 || plotIndex%10 == 9)) //first part checks sides and !toprow && !bottomrow. second part checks if left or right side
                return false;
        }
        if(player.plant(seedList.get(seedIndex), plot.get(plotIndex))){
            updatePlotMap();
            return true;
        }
        else
            return false;
    }

    /**
     * This method calls {@link farm.Player#useTool(Tool, Tile)}.
     * If the method call returned true then {@link GUI.FarmModel#updatePlotMap()} is called.
     * @param plotIndex index of the {@link farm.Tile} object in {@link GUI.FarmModel#plot} that the tool will be used on.
     * @param toolIndex index of the tool to be used in {@link GUI.FarmModel#toolList}.
     * @return true if tool use was successful, false otherwise.
     */
    public boolean useTool(int plotIndex, int toolIndex){
        if(player.useTool(toolList.get(toolIndex), plot.get(plotIndex))){
            updatePlotMap();
            return true;
        }
        return false;
    }

    /**
     * This method calls {@link farm.Upgradeable#upgrade()} and changes {@link GUI.FarmModel#player} if
     * upgrade was successful.
     * @return true if player was upgraded, false otherwise.
     */
    public boolean upgradePlayer(){
        Player newPlayer = ((Upgradeable) player).upgrade();
        if(newPlayer != null){
            if(!newPlayer.getClass().isInstance(player)){
                player = newPlayer;
                return true;
            }
        }   
        return false;
    }
    
    /**
     * This method reads the plot map from a text file and stores it in {@link GUI.FarmModel#plotMap}.
     * @throws FileNotFoundException if the text file was not found.
     */
    private void initPlotMap() throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("../items/mapInit.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter(",|\\r\\n");
        int i = 0;
        this.plotMap = new int[50];
        while(txtFileReader.hasNext()){
            plotMap[i] = Integer.parseInt(txtFileReader.next());
            i++;
        }   
        txtFileReader.close();
    }
    /**
     * Gets the {@link farm.Player} object stored in this class.
     * @return the {@link farm.Player} object stored in this class.
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Gets a {@link farm.Tile} object stored in {@link GUI.FarmModel#plot}.
     * @param i index of the {@link farm.Tile} object stored in {@link GUI.FarmModel#plot}.
     * @return {@link farm.Tile} object stored in index i of {@link GUI.FarmModel#plot}.
     */
    public Tile getPlot(int i){
        return plot.get(i);
    }

    /**
     * Gets a {@link seeds.Crop} object in {@link GUI.FarmModel#seedList}.
     * @param i index of the {@link seeds.Crop} object in {@link GUI.FarmModel#seedList}.
     * @return {@link seeds.Crop} object stored in index i of {@link GUI.FarmModel#seedList}.
     */
    public Crop getCrop(int i){
        return seedList.get(i);
    }

    /**
     * Checks if the game has any active/growing crops in the field.
     * @return true if there are active crops. False otherwise.
     */
    private boolean checkStatus(){
        int ctr = 0;
        if(player.getObjectCoins() >= 5){
            for(int i = 0; i < plot.size(); i++){
                if(plot.get(i).getCrop() != null) //Assumes that if a plot already has a plant, then there should be no rock and is plowed
                    if(!plot.get(i).IsPlantDead()) //nested in order to avoid errors when plot crop is null
                        ctr++;
            }
        }
        if (ctr > 0)
            return true; //game keeps going when there are active plants growing and/or not dead
        return false; //game ends when no alive plants are found
    }

    /**
     * Gets a {@link tools.Tool} in {@link GUI.FarmModel#toolList}.
     * @param i index of a {@link tools.Tool} in {@link GUI.FarmModel#toolList}.
     * @return {@link tools.Tool} in index i of {@link GUI.FarmModel#toolList}.
     */
    public Tool getTool(int i){
        return this.toolList.get(i);
    }

    /**
     * Gets the day number
     * @return the day number
     */
    public int getDay(){
        return day;
    }

    /**
     * Updates {@link GUI.FarmModel#plotMap} based on the current status of {@link GUI.FarmModel#plot}.
     * <p>
     * {@link GUI.FarmModel#plotMap}[i] will be set to the following depending on the status of {@link GUI.FarmModel#plot}[i].
     * <p><ul>
     * <li> 0 - empty unplowed tile
     * <li> 1 - tile with rock
     * <li> 2 - tile with seed
     * <li> 3 - harvestable turnip
     * <li> 4 - harvestable carrot
     * <li> 5 - harvestable potato
     * <li> 6 - harvestable rose
     * <li> 7 - harvestable tulip
     * <li> 8 - harvestable sunflower
     * <li> 9 - harvestable apple tree
     * <li> 10 - harvestable mango tree
     * <li> 11 - withered/dead plant
     * </ul>
     */
    private void updatePlotMap(){
        Tile temp;
        Crop tempCrop;
        for(int i = 0; i < plot.size(); i++){ //go through all plots
            temp = plot.get(i); 
            if(temp.getCrop() != null){ //if there is a crop
                tempCrop = temp.getCrop();
                if(temp.getTimeTilHarvest() == 0 && !temp.IsPlantDead()){ //check if harvestable
                    for(int j =0 ; j < seedList.size(); j++){ //scan through the seedList and check which seed was planted
                        if(tempCrop.getName() == seedList.get(j).getName()){
                            plotMap[i] = j+3; //index of the seed in the seedList + 3 = the corresponding harvestable status of that seed
                        }
                    }
                }
                else if(temp.IsPlantDead()){
                    plotMap[i] = 11; //Plant is withered
                }
                else{
                    plotMap[i] = 2;//Still a seed
                }
            }
            else if(temp.getRock()){
                plotMap[i] = 1; //Has a rock
            }
            else
                plotMap[i] = 0;//Empty unplowed tile
        }
    }

    /**
     * This method initializes {@link GUI.FarmModel#seedList} from a text file.
     * @throws FileNotFoundException if the text file is not found.
     */
    private void seedsInit() throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("../items/seeds.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\\n"); //to separate the different text in the text file
        String cropType;
        String cropName;
        int harvestTime;
        int waterNeeds;
        int fertNeeds;
        int prodMin = 1;
        int prodMax = 2;
        double cost;
        int bsp;
        double exp;
        while(txtFileReader.hasNext()){
            cropType = txtFileReader.next();
            cropName = txtFileReader.next();
            harvestTime = txtFileReader.nextInt();
            waterNeeds = txtFileReader.nextInt();
            fertNeeds = txtFileReader.nextInt();
            if(cropType.equals("RootCrop") || cropType.equals("Tree")){
                prodMin = txtFileReader.nextInt();
                prodMax = txtFileReader.nextInt();
            }
            cost = Double.valueOf(txtFileReader.next()); //turns the string into double, nextDouble() does not work
            bsp = txtFileReader.nextInt();
            exp = Double.valueOf(txtFileReader.next());
            switch(cropType){
                case "RootCrop": seedList.add(new RootCrop(cropName, waterNeeds, fertNeeds, harvestTime, bsp, cost, exp, prodMin, prodMax)); break;
                case "Tree": seedList.add(new Tree(cropName, waterNeeds, fertNeeds, harvestTime, bsp, cost, exp, prodMin, prodMax)); break;
                case "Flower": seedList.add(new Flower(cropName, waterNeeds, fertNeeds, harvestTime, bsp, cost, exp)); break;
            }
            
        }   
        txtFileReader.close(); 
    }
    /**
     * This method initializes all the {@link tools.Tool} in {@link GUI.FarmModel#toolList}.
     */
    private void toolsInit(){
        toolList.add(new Plow("Plow",0.0, 0.5));
        toolList.add(new WateringCan("Watering Can", 0.0, 0.5));
        toolList.add(new Fertilizer("Fertilizer", 10.0, 4));
        toolList.add(new Pickaxe("Pickaxe", 50.0, 15));
        toolList.add(new Shovel("Shovel", 7.0, 2));
    }

    /**
     * This method checks if the sides of a {@link farm.Tile} in {@link GUI.FarmModel#plot}.
     * are empty. This includes the diagonals.
     * @param index index of the {@link farm.Tile} in {@link GUI.FarmModel#plot} to be checked.
     * @return true if the sides of the {@link farm.Tile} are empty and it is not in the top or bottom row. False otherwise.
     */
    private boolean arePlotSidesEmpty(int index){
        if(index > 9 && index < 39)
            if(plot.get(index+1).isEmpty() &&
                plot.get(index-1).isEmpty() &&
                plot.get(index+10).isEmpty() &&
                plot.get(index-10).isEmpty() &&
                plot.get(index+11).isEmpty() &&
                plot.get(index-11).isEmpty() &&
                plot.get(index+9).isEmpty() &&
                plot.get(index-9).isEmpty())
                    return true;
        return false; 
    }
    /**
     * Gets plotMap of the game.
     * @return plotMap of the game.
     */
    public int[] getPlotMap(){
        return plotMap;
    }
}
