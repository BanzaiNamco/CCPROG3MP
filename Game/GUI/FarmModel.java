package GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0
    };
    /*private ArrayList<Tool> tools;
*/
    
    public FarmModel(String name) throws FileNotFoundException{
        player = new Farmer(name);
        this.plot = new ArrayList<Tile>();
        this.seedList = new ArrayList<Crop>();

        for (int i = 0; i < 50; i++){
            plot.add(new Tile(plotMap[i]));
        }
        seedsInit();
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
        for(int i = 0; i < plot.size(); i++){
            if(plot.get(i).getCrop() != null && !plot.get(i).getCrop().getDead())
                ctr++;
        }
        if (ctr > 0)
            return true;
        return false;
    }

    private void seedsInit() throws FileNotFoundException{
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
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

    /*public FarmModel(Player player, ArrayList<Tool> tools, ArrayList<Tile> plot){
        this.player = player;
        this.plot = new ArrayList<Tile>(plot); //create a map, max # of rocks, etc
        this.tools = new ArrayList<Tool>(tools);

        
    }*/ 
    /*private static void toolsInit(ArrayList<Tool> tools) throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("items/tools.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
        while(txtFileReader.hasNext()){
            String toolName = txtFileReader.next();
            int useCost = txtFileReader.nextInt();
            double exp = Double.valueOf(txtFileReader.next());
            
            tools.add(new Tool(toolName, useCost, exp));
        }
        txtFileReader.close();
    }*/
}
