package GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import farm.*;
import seeds.*;
import tools.*;


public class FarmModel {
    private Player player;
    private ArrayList<Tile> plot;
    private ArrayList<Crop> seedList;
    private ArrayList<Tool> toolList;
    
    private int[] plotMap = {
        1, 0, 0, 0, 0, 0, 1, 0, 1, 0,
        0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
        1, 0, 0, 0, 1, 1, 0, 0, 1, 0,
        0, 1, 0, 1, 0, 0, 0, 1, 0, 1,
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0 //TODO turn this into file input
    };
    
    public FarmModel(String name) throws FileNotFoundException{
        player = new Farmer(name);
        this.plot = new ArrayList<Tile>();
        this.seedList = new ArrayList<Crop>();
        this.toolList = new ArrayList<Tool>();

        for (int i = 0; i < 50; i++){
            plot.add(new Tile(plotMap[i]));
        }
        seedsInit();
        toolsInit();
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
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
    public boolean checkStatus(){
        int ctr = 0;
        if(player.getObjectCoins() >= 5){
            for(int i = 0; i < plot.size(); i++){
                if(plot.get(i).getCrop() != null && !plot.get(i).getCrop().getDead())
                    ctr++;
            }
        }
        if (ctr > 0)
            return true;
        return false;
    }

    public Tool getTool(int i){
        return this.toolList.get(i);
    }

    public int[] getPlotMap(){
        int map[] = new int[plot.size()];
        Tile temp;
        Crop tempCrop;
        for(int i = 0; i < plot.size(); i++){
            temp = plot.get(i);
            if(temp.getCrop() != null){
                tempCrop = temp.getCrop();
                if(tempCrop.getHarvestTime() == 0){
                    //check instanceof
                }
                else if(tempCrop.getDead()){
                    //set to dead
                }
                else{
                    map[i] = 3;
                }
            }
            else if(temp.getRock()){
                map[i] = 2;
            }
            else
                map[i] = 0;
        }
        return map;
    }

    private void seedsInit() throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("../items/seeds.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
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
        toolList.add(new Plow(0, 0.5));
        toolList.add(new WateringCan(0, 0.5));
        toolList.add(new Fertilizer(10, 4));
        toolList.add(new Pickaxe(50, 15));
        toolList.add(new Shovel(7, 2));
    }
}
