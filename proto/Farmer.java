package proto;

import java.util.ArrayList;

public class Farmer extends Player{
    private String farmer_type;
    private int bonusEarn;
    private int minusSeedCost;
    private int bonusWater;
    private int bonusFertilizer;
    ArrayList<Tools> toolList;
    Farm myFarm;

    public Farmer(String name, Farm myFarm, ArrayList<Tools> toolList){
        super(name);
        this.farmer_type = "Farmer";
        this.bonusEarn = 0;
        this.minusSeedCost = 0;
        this.bonusWater = 0;
        this.bonusFertilizer = 0;
        this.toolList = new ArrayList<Tools>();
        this.toolList = toolList;
        this.myFarm = myFarm;
    }
    public void harvest(int i){
        if(this.myFarm.canHarvest(i)){
            this.gainExp(this.myFarm.getPlot(i).getSeed().getExpYield());
            //earn money - harvestTotal
            
            this.myFarm.getPlot(i).resetTile();
        }
    }

    public void Register(){
        if(this.farmer_type.equals("Farmer")){
            if(this.getLevel() >= 5 && this.getObjectCoins() >= 200){
                this.farmer_type = "Registered Farmer";
                this.bonusEarn = 1;
                this.minusSeedCost = 1;
                this.useObjectCoins(200);
            }
            else{
                //not high enough level
            }
        }
        else if(this.farmer_type.equals("Registered Farmer")){
            if(this.getLevel() >= 10 && this.getObjectCoins() >= 300){
                this.farmer_type = "Distinguished Farmer";
                this.bonusEarn = 2;
                this.minusSeedCost = 2;
                this.bonusWater = 1;
                this.useObjectCoins(300);
            }
            else{
                //not allowed
            }
        }
        else if(this.farmer_type.equals("Distinguished Farmer")){
            if(this.getLevel() >=15 && this.getObjectCoins() >= 400){
                this.farmer_type = "Legendary Farmer";
                this.bonusEarn = 4;
                this.minusSeedCost = 3;
                this.bonusWater = 2;
                this.bonusFertilizer = 1;
                this.useObjectCoins(400);
            }
        }
        else if (this.farmer_type.equals("Legendary Farmer")){
            //bro
        }
        else{
            //error
        }
    }
    public String getFarmerType(){
        return this.farmer_type;
    }
    public int getBonusEarn(){
        return this.bonusEarn;
    }
    public int getMinusSeedcost(){
        return this.minusSeedCost;
    }
    public int getBonusWater(){
        return this.bonusWater;
    }
    public int getBonusFertilizer(){
        return this.bonusFertilizer;
    }
    //toollist and myfarm, not sure if getters needed
}
