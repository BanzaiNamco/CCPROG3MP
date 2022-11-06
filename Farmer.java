//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles actions of the farmer in the game
 */

public class Farmer extends Player{

    private String farmer_type;
    private int bonusEarn;
    private int minusSeedCost;
    private int bonusWater;
    private int bonusFertilizer;
    
    public Farmer(String name){
        super(name);
        this.farmer_type = "Farmer";
        this.bonusEarn = 0;
        this.minusSeedCost = 0;
        this.bonusWater = 0;
        this.bonusFertilizer = 0;
    }
    
    /**
     * Harvests the plant from the tile that is passed through the parameter
     * <p>
     * If a plant was harvested, this method updates the player's exp and objectcoins
     * and calls the resetTile method of the Tile object.
     * 
     * @param e  the Tile object to be harvested
     * @return  true if a plant was harvested from e, otherwise false
     */
    public boolean harvest(Tile e, int harvestTotal){
        if(e.getSeed() != null){
            if(e.getSeed().getHarvestable()){

                this.gainExp(e.getSeed().getExpYield());
                System.out.println("Gained " + e.getSeed().getExpYield() + " exp!");
                this.gainObjectCoins(harvestTotal);
                System.out.println("Gained " + harvestTotal + " objectCoins!");
                e.resetTile();
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method checks if tool is a farmer recognized tool 
     * then calls the appropriate action method if applicable
     * 
     * @param tool  tool object to be used by the farmer
     * @param e  tile where the tool will be used on
     */
    public void useTool(Tool tool, Tile e){
        switch(tool.getName()){
            case "Plow":
                this.plow(tool, e);
                break;
            case "WateringCan":
                this.water(tool, e);
                break;
            case "Fertilizer" :
                this.fertilize(tool, e);
                break;
            case "Pickaxe" :
                this.pickaxe(tool, e);
                break;
            case "Shovel" :
                this.shovel(tool, e);
                break;
            default:
                System.out.println("Error : unknown tool");
        }
    }
    /**
     * Attempts to add a seed object to tile
     * @param seed  seed to be planted into tile
     * @param e  tile where seed is to be planted on
     */
    public void plantSeed(Seed seed, Tile e){
        
        if(e.plant(new Seed(seed))){
            this.useObjectCoins(e.getSeed().getCost());
            System.out.println("Used " + e.getSeed().getCost() + " objectCoins");
        }
        else{
            System.out.println("Could not plant seed");
        }
    }
    /**
     * Updates the farmer type and all applicable bonuses
     */
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
    
    /**
     * Returns farmer type
     * @return farmer_type
     */
    public String getFarmerType(){
        return this.farmer_type;
    }
    /**
     * Returns the bonus earnings of the player
     * @return bonusEarn
     */
    public int getBonusEarn(){
        return this.bonusEarn;
    }
    /**
     * Returns the seed cost reduction of the player
     * @return minusSeedCost
     */
    public int getMinusSeedCost(){
        return this.minusSeedCost;
    }
    /**
     * Returns the bonus water limit
     * @return bonusWater
     */
    public int getBonusWater(){
        return this.bonusWater;
    }
    /**
     * Returns the bonus fertilizer limit
     * @return bonusFertilizer
     */
    public int getBonusFertilizer(){
        return this.bonusFertilizer;
    }
    /**
     * Attemps to plow the given tile and calls the toolUsed() method
     * if successful
     * <p>
     * This method assumes the tool object being passed is the plow tool
     * @param tool  tool object to be used
     * @param e  tile to be plowed
     */
    private void plow(Tool tool, Tile e){
        if(e.plow()){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Plow unsuccessful");
        }
    }
    /**
     * Attemps to water the seed in the given tile if present
     * and calls the toolUsed() method if successful
     * <p>
     * This method assumes the tool passed is the watering can
     * @param tool  tool object to be used
     * @param e  tile with the seed to be watered
     */
    private void water(Tool tool, Tile e){
        if(e.water(this.bonusWater)){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Watering unsuccessful");
        }
    }
    /**
     * Attempts to fertilize the seed in the given tile if present
     * and calls the toolUsed() method if successful
     * <p>
     * This method assumes the tool passed is the fertilizer
     * @param tool  tool object to be used
     * @param e  tile with the seed to be fertilized
     */
    private void fertilize(Tool tool, Tile e){
        if(e.fertilize(this.bonusFertilizer)){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Fertilizing unsuccessful");
        }
    }
    /**
     * Attempts to use the pickaxe tool on the given tile
     * <p>
     * This method assumes the tool being passed is the pickaxe
     * @param tool  tool object to be used
     * @param e  tile where the pickaxe is to be used
     */
    private void pickaxe(Tool tool, Tile e){
        if(e.pickaxe()){
            this.toolUsed(tool);
        }
        else{
            System.out.println("Cannot use pickaxe");
        }
    }
    /**
     * Uses the shovel on the given tile
     * Object coins are always deducted when the shovel is used
     * If a withered plant is removed, then the toolUsed() method is called instead
     * @param tool  tool object to be used
     * @param e  tile where the shovel will be used on
     */
    private void shovel(Tool tool, Tile e){
        switch(e.shovel()){
            case 0: 
                this.toolUsed(tool); 
                System.out.println("Withered plant removed!"); 
                break;
            case 1:
                this.useObjectCoins(tool.getUseCost());
                System.out.println("Wasted " + tool.getUseCost() + " object coins");
                System.out.println("Plant removed!");
                break;
            case 2:
                this.useObjectCoins(tool.getUseCost());
                System.out.println("Wasted " + tool.getUseCost() + " object coins");
                System.out.println("Shovel used on nothing!");
                break;
            case 3:
                System.out.println("error"); break;
            default:
                System.out.println("error 2");
        }
    }
    //This method adds the player's exp and reduces their objectcoins
    //depending on the tool used
    /**
     * Adds the player exp and deducts the player's objectCoins based
     * on the tool expOnUse and tool useCost
     * @param tool  tool that is being used
     */
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
