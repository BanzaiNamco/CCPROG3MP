//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles actions of the farmer in the game
 */

public class Farmer{

    private double objectCoins = 100;
    private double exp = 0;
    private int level = 0;
    private final String name;
    private String farmer_type = "Farmer";
    private int bonusEarn = 0;
    private int minusSeedCost = 0;
    private int bonusWater = 0;
    private int bonusFertilizer = 0;
    
    public Farmer(String name){
        this.name = name;
    }
    /**
     * Attempts to add a seed object to tile
     * @param seed  seed to be planted into tile
     * @param e  the tile where seed is to be planted in
     */
    public void plantSeed(Seed seed, Tile e){
        if(e.plant(new Seed(seed))){
            int cost = e.getSeed().getCost();
            this.useObjectCoins(cost);
        }
        else{
            System.out.println("Could not plant seed");
        }
    }
    /**
     * Attempts to plow a tile
     * <p>

     * @param tool  plowing tool to be used
     * @param e  tile to be plowed
     */
    public void plow(Tool tool, Tile e){
        if(tool.getName().equals("Plow"))
            if(e.plow())
                this.toolUsed(tool);
    }
    /**
     * Attemps to water a plant
     * <p>
     * @param tool  watering can to be used
     * @param e  tile to be watered
     */
    public void water(Tool tool, Tile e){
        if(tool.getName().equals("WateringCan"))
            if(e.water(bonusWater))
                this.toolUsed(tool);
    }
    /**
     * Attempts to fertilize a plant
     * <p>
     * @param tool  fertilizer to be used
     * @param e  tile with plant to be fertilized
     */
    public void fertilize(Tool tool, Tile e){
        if(tool.getName().equals("Fertilizer"))
            if(e.fertilize(bonusFertilizer))
                this.toolUsed(tool);
    }
    /**
     * Attempts to use the pickaxe tool on the given tile
     * <p>
     * 
     * @param tool  tool object to be used
     * @param e  tile where the pickaxe is to be used
     */
    public void pickaxe(Tool tool, Tile e){
        if(tool.getName().equals("Pickaxe"))
            if(e.pickaxe())
                this.toolUsed(tool);
    }
    /**
     * Uses the shovel on the given tile
     * Object coins are always deducted when the shovel is used
     * If a withered plant is removed, then the toolUsed() method is called instead
     * @param tool  tool object to be used
     * @param e  tile where the shovel will be used on
     */
    public void shovel(Tool tool, Tile e){
        if(tool.getName().equals("Shovel"))
            switch(e.shovel()){
                case 0: 
                    this.toolUsed(tool); 
                    break;
                case 1:
                case 2:
                    this.useObjectCoins(tool.getUseCost());
                    System.out.println("Wasted " + tool.getUseCost() + " object coins");
                    break;
                case 3:
                    System.out.println("error"); break;
                default:
                    System.out.println("error 2");
            }
    }
    /**
     * Adds the player exp and deducts the player's objectCoins based
     * on the tool expOnUse and tool useCost
     * @param tool  tool that is being used
     */
    public void toolUsed(Tool tool){
        this.gainExp(tool.getExpOnUse());
        this.useObjectCoins(tool.getUseCost());
    }

    /**
     * This method adds to the current exp of the player
     * and displays the amount of exp earned
     * @param exp  amount of exp to be added
     */
    public void gainExp(double exp){
        this.exp += exp;
        System.out.println("Gained " + exp + " exp!");
    }
    /**
     * This method adds objectCoins to the player's objectCoin count
     * and displays the amount of objectCoins earned
     * @param objectCoins  amount of objectCoins to be added
     */
    public void gainObjectCoins(double objectCoins){
        this.objectCoins += objectCoins;
        System.out.println("Gained " + objectCoins + " objectCoins!");
    }
    /**
     * This method deducts the player's objectCoins
     * and displays the amount of objectCoins used
     * @param objectCoins  amount of objectCoins to be deducted
     */
    public void useObjectCoins(double objectCoins){
        this.objectCoins -= objectCoins;
        System.out.println("Used " + objectCoins + " objectCoins!");
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
    public double getExp(){
        return this.exp;
    }
    public int getLevel(){
        return this.level;
    }
    public String getName(){
        return this.name;
    }
    public double getObjectCoins(){
        return this.objectCoins;
    }
    /**
     * This method updates the player level depending on the amount of exp the player has
     */
    public void update(){
        if (this.level <= Math.floor(exp/100) && this.exp > (this.level * 100) + 99){ //probably a better way to do this tbh
            this.level++;
        }
    }
    //FOR TESTING USE ONLY, REMOVE CODE IN FINAL BUILD
    public void addEXP(double xp){
        this.gainExp(xp);
    }
}
