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
 *///TODO add more?
public class FarmModel {
    private Player player;
    private ArrayList<Tile> plot;
    private ArrayList<Crop> seedList;
    private ArrayList<Tool> toolList;
    private int day = 1;
    private int[] plotMap;

    /**
     * This constructor initializes the game to its start.
     * @param name name of the player
     * @throws FileNotFoundException when the files containing vital game object stats are missing
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
     * This method updates the entirety of {@link GUI.FarmModel#plot} and increments the day counter
     * <p>
     * This method calls {@link GUI.FarmModel#checkStatus()} and stores it into the variable status
     * before doing any updating.
     * @return status
     */
    public boolean advanceDay(){ //TODO double check
        boolean status = checkStatus(); //check end of the day status before moving forward
        for(int i = 0; i < plot.size(); i++){
            plot.get(i).update();
            updatePlotMap();
        }
        day++;
        return status;
    }

    /**
     * This method calls {@link farm.Player#update()}
     * @return {@link farm.Player#update()}
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
        if(plot.get(tileIndex).getCrop() != null){
            if(!plot.get(tileIndex).IsPlantDead() && plot.get(tileIndex).getTimeTilHarvest() == 0){
                double harvestTotal = getHarvestTotal(tileIndex);
                player.gainExp(plot.get(tileIndex).getCrop().getExpYield());
                player.addObjectCoins(harvestTotal);
                plot.get(tileIndex).resetTile();
                updatePlotMap();
                return true;
            }
        }
        return false;
    }

    public double getHarvestTotal(int tileIndex){
        double harvestTotal = (plot.get(tileIndex).getNumOfProduce()) 
                                * (plot.get(tileIndex).getCrop().getBaseSellingPrice() + ((Farmer)player).getBonusEarn());
        double waterBonus = getWaterBonus(harvestTotal, plot.get(tileIndex));
        double fertBonus = getFertBonus(harvestTotal, plot.get(tileIndex));
        harvestTotal += waterBonus + fertBonus;
        if(plot.get(tileIndex).getCrop() instanceof Flower)
            harvestTotal *= 1.1;
            
        return harvestTotal;
    }

    private double getWaterBonus(double harvestTotal, Tile tile){
        if(tile.getTimesWatered() > tile.getCrop().getWaterLimit() + ((Farmer)player).getBonusWater())
            return harvestTotal * 0.2 * (tile.getCrop().getWaterLimit() + ((Farmer)player).getBonusWater() - 1);
        return harvestTotal * 0.2 * (tile.getTimesWatered()-1);
    }
    private double getFertBonus(double harvestTotal, Tile tile){
        if(tile.getTimesFertilized() > tile.getCrop().getFertilizerLimit() + ((Farmer)player).getBonusFert())
            return harvestTotal * 0.5 * (tile.getCrop().getFertilizerLimit() + ((Farmer)player).getBonusFert());
        return harvestTotal * 0.5 * tile.getTimesFertilized();
    }

    public boolean plant(int plotIndex, int seedIndex){
        if(seedList.get(seedIndex) instanceof Tree){
            if(!arePlotSidesEmpty(plotIndex) || (plotIndex%10 == 0 || plotIndex%10 == 9))
                return false;
        }
        if(player.plant(seedList.get(seedIndex), plot.get(plotIndex))){
            updatePlotMap();
            return true;
        }
        else
            return false;
    }

    public boolean useTool(int plotIndex, int toolIndex){
        if(player.useTool(toolList.get(toolIndex), plot.get(plotIndex))){
            updatePlotMap();
            return true;
        }
        return false;
    }

    public boolean upgradePlayer(){
        Player newPlayer = ((Upgradeable) player).upgrade();
        if(!newPlayer.getClass().isInstance(player)){
            player = newPlayer;
            return true;
        }
        return false;
    }
    
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
    public Player getPlayer(){
        return player;
    }
    public Tile getPlot(int i){
        return plot.get(i);
    }
    public int getPlotSize(){
        return plot.size();
    }
    public Crop getCrop(int i){
        return seedList.get(i);
    }

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

    public Tool getTool(int i){
        return this.toolList.get(i);
    }

    public int getDay(){
        return day;
    }

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

    private void seedsInit() throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("../items/seeds.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\\n");
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
            cost = Double.valueOf(txtFileReader.next());
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
    private void toolsInit(){
        toolList.add(new Plow("Plow",0.0, 0.5));
        toolList.add(new WateringCan("Watering Can", 0.0, 0.5));
        toolList.add(new Fertilizer("Fertilizer", 10.0, 4));
        toolList.add(new Pickaxe("Pickaxe", 50.0, 15));
        toolList.add(new Shovel("Shovel", 7.0, 2));
    }
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
    public int[] getPlotMap(){
        return plotMap;
    }
}
