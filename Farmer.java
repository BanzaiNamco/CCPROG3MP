import java.util.ArrayList;

public class Farmer extends Player{

    private String farmer_type;
    private int bonusEarn;
    private int minusSeedCost;
    private int bonusWater;
    private int bonusFertilizer;
    ArrayList<Tile> myFarm;
    
    public Farmer(String name, ArrayList<Tile> myFarm){
        super(name);
        this.farmer_type = "Farmer";
        this.bonusEarn = 0;
        this.minusSeedCost = 0;
        this.bonusWater = 0;
        this.bonusFertilizer = 0;
        this.myFarm = new ArrayList<Tile>(myFarm);
    }
    
    public void harvest(int i){
        if(this.myFarm.get(i).requirementsMet()){
            double expYield = this.myFarm.get(i).getSeedExpYield();
            int harvestTotal = this.myFarm.get(i).calcHarvestTotal(this.bonusEarn);
            
            this.gainExp(expYield);
            this.gainObjectCoins(harvestTotal);
            this.myFarm.get(i).resetTile();
            
            System.out.println("Harvest Total is" + harvestTotal);
            System.out.println("Exp gained " + expYield);
        }
        else{
            System.out.println("Harvest unsuccessful");
        }
    }
    public void useTool(Tool tool, int i){
        switch(tool.getName()){
            case "Plow":
                this.plow(tool, i);
                break;
            case "WateringCan":
                this.water(tool, i);
                break;
            case "Fertilizer" :
                this.fertilize(tool, i);
                break;
            case "Pickaxe" :
                this.pickaxe(tool, i);
                break;
            case "Shovel" :
                this.shovel(tool, i);
                break;
            default:
                System.out.println("Error : unknown tool");
        }
    }
    public void plantSeed(Seed seed, int i){
        if(!this.myFarm.get(i).plant(seed))
            System.out.println("Could not plant seed");
    }
    public void registerFarmer(){
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
    public int getMinusSeedCost(){
        return this.minusSeedCost;
    }
    public int getBonusWater(){
        return this.bonusWater;
    }
    public int getBonusFertilizer(){
        return this.bonusFertilizer;
    }
    
    private void plow(Tool tool, int i){
        if(this.myFarm.get(i).plow()){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Plow unsuccessful");
        }
    }
    private void water(Tool tool, int i){
        if(this.myFarm.get(i).water(this.bonusWater)){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Watering unsuccessful");
        }
    }
    private void fertilize(Tool tool, int i){
        if(this.myFarm.get(i).fertilize(this.bonusFertilizer)){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Fertilizing unsuccessful");
        }
    }
    private void pickaxe(Tool tool, int i){
        if(this.myFarm.get(i).pickaxe()){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Cannot use pickaxe");
        }
    }
    private void shovel(Tool tool, int i){
        switch(this.myFarm.get(i).shovel()){
            case 0: this.toolUsed(tool); System.out.println("Withered plant removed!"); break;
            case 1: this.myFarm.get(i).resetTile(); this.useObjectCoins(tool.getUseCost()); System.out.println("Plant removed!"); break;
            case 2: this.useObjectCoins(tool.getUseCost()); System.out.println("Shovel used on nothing!"); break;
            case 3: System.out.println("error"); break;
            default: System.out.println("error 2");
        }
    }
    private void toolUsed(Tool tool){
        this.gainExp(tool.getExpOnUse());
        System.out.println("Gained " + tool.getExpOnUse() + " exp");
        this.useObjectCoins(tool.getUseCost());
        System.out.println("Used " + tool.getUseCost() + " objectCoins");
    }

    //FOR TESTING USE ONLY, REMOVE CODE IN FINAL BUILD
    public void addEXP(double xp){
        this.gainExp(xp);
    }
}
