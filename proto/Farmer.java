package proto;

import java.util.ArrayList;

public class Farmer extends Player{
    private String farmer_type;
    private int bonusEarn;
    private int minusSeedCost;
    private int bonusWater;
    private int bonusFertilizer;
    ArrayList<Tile> myFarm;
    ArrayList<Seed> seedDispensary;

    public Farmer(String name, ArrayList<Tile> myFarm){
        super(name);
        this.farmer_type = "Farmer";
        this.bonusEarn = 0;
        this.minusSeedCost = 0;
        this.bonusWater = 0;
        this.bonusFertilizer = 0;
        this.myFarm = new ArrayList<Tile>(myFarm);
        this.seedDispensary = new ArrayList<Seed>();
    }
    public void harvest(int i){
        
        int harvestTotal = this.myFarm.get(i).getRandomProduce() * (this.myFarm.get(i).getSeedBSP() + this.bonusEarn);
        harvestTotal += this.myFarm.get(i).getWaterBonus(harvestTotal) + this.myFarm.get(i).getFertilizerBonus(harvestTotal);

        this.gainObjectCoins(harvestTotal);
        this.gainExp(this.myFarm.get(i).getSeedExpYield());
        this.myFarm.get(i).resetTile();
    }
    public void useTool(Tool tool, int i){
        switch(tool.getName()){
            case "Plow":
                if(this.myFarm.get(i).plow()){
                    this.toolUsed(tool);
                }
            case "Watering Can":
                if(this.myFarm.get(i).water(this.bonusWater)){
                    this.toolUsed(tool);
                }
            case "Fertilizer" :
                if(this.myFarm.get(i).fertilize(this.bonusFertilizer)){
                    this.toolUsed(tool);
                }
            case "Pickaxe" :
                if(this.myFarm.get(i).pickaxe()){
                    this.toolUsed(tool);
                }
            case "Shovel" :
                switch(this.myFarm.get(i).shovel()){
                    case 0: this.gainExp(tool.getExpOnUse());
                    case 1: ;
                    case 2: this.useObjectCoins(tool.getUseCost()); break;
                    case 3: System.out.println("error");
                    default: System.out.println("error 2");
                }
        }
    }
    private void toolUsed(Tool tool){
        this.gainExp(tool.getExpOnUse());
        this.useObjectCoins(tool.getUseCost());
    }
    public void plantSeed(int i, Seed seed){
        if(!this.myFarm.get(i).plant(seed))
            System.out.println("Could not plant seed");
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
