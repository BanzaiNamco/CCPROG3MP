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
        if(this.myFarm.get(i).requirementsMet()){
            System.out.println("beofre harvest");
            int harvestTotal = this.myFarm.get(i).getRandomProduce(); 
            System.out.println("rando");
            harvestTotal *= (this.myFarm.get(i).getSeedBSP() + this.bonusEarn);
            System.out.println("harvesttotal");
            harvestTotal += this.myFarm.get(i).getWaterBonus(harvestTotal) + this.myFarm.get(i).getFertilizerBonus(harvestTotal);
            System.out.println("final");

            this.gainObjectCoins(harvestTotal);
            System.out.println("Harvest Total is" + harvestTotal);
            System.out.println("Exp gained " + this.myFarm.get(i).getSeedExpYield());
            this.gainExp(this.myFarm.get(i).getSeedExpYield());
            this.myFarm.get(i).resetTile();
        }
        else{
            System.out.println("Harvest unsuccessful");
        }
    }
    public void useTool(Tool tool, int i){
        switch(tool.getName()){
            case "Plow":
                if(this.myFarm.get(i).plow()){
                    this.toolUsed(tool);
                }
                else{
                    System.out.println("Plow unsuccessful");
                }
                break;
            case "WateringCan":
                if(this.myFarm.get(i).water(this.bonusWater)){
                    this.toolUsed(tool);
                }
                else{
                    System.out.println("Watering unsuccessful");
                }
                break;
            case "Fertilizer" :
                if(this.myFarm.get(i).fertilize(this.bonusFertilizer)){
                    this.toolUsed(tool);
                }
                else{
                    System.out.println("Fertilizing unsuccessful");
                }
                break;
            case "Pickaxe" :
                if(this.myFarm.get(i).pickaxe()){
                    this.toolUsed(tool);
                }
                else{
                    System.out.println("Cannot use pickaxe");
                }
                break;
            case "Shovel" :
                switch(this.myFarm.get(i).shovel()){
                    case 0: this.toolUsed(tool); System.out.println("Withered plant removed!"); break;
                    case 1: this.myFarm.get(i).resetTile(); this.useObjectCoins(tool.getUseCost()); System.out.println("Plant removed!"); break;
                    case 2: this.useObjectCoins(tool.getUseCost()); System.out.println("Shovel used on nothing!"); break;
                    case 3: System.out.println("error"); break;
                    default: System.out.println("error 2");
                }
        }
    }
    private void toolUsed(Tool tool){
        this.gainExp(tool.getExpOnUse());
        System.out.println("Gained " + tool.getExpOnUse() + " exp");
        this.useObjectCoins(tool.getUseCost());
        System.out.println("Used " + tool.getUseCost() + " objectCoins");
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
