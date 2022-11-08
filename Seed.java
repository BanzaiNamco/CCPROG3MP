//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles all the information and informaiton updates
 * of a seed in the game
 */

public class Seed {
    private final String name;
    private final String cropType;
    private int waterNeed;
    private int fertilizerNeed;
    private int waterLimit;
    private int fertilizerLimit;
    private int harvestTime;
    private int baseSellingPrice;
    private int produceMin;
    private int produceMax;
    private int cost;
    private double expYield;
    private int timesWatered = 0;
    private int timesFertilized = 0;


    public Seed(String name, String type, int harvestTime, int waterNeed, int fertNeed, int prodMin, int prodMax,
                    int cost, int bsp, double exp){
        this.name = name;
        this.cropType = type;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertNeed;
        if(waterNeed != 7 && fertNeed < 4){
            this.waterLimit = waterNeed + 1;
            this.fertilizerLimit = fertNeed + 1;
        }
        else{
            this.waterLimit = waterNeed;
            this.fertilizerLimit = fertNeed;
        }
        this.produceMin = prodMin;
        this.produceMax = prodMax;
        this.cost = cost;
        this.baseSellingPrice = bsp;
        this.expYield = exp;
    }
    //This constructor creats a clone of the seed object being passed
    public Seed(Seed e){
        this.name = e.getName();
        this.cropType = e.getCropType();
        this.harvestTime = e.getHarvestTime();
        this.waterNeed = e.getWaterNeed();
        this.fertilizerNeed = e.getFertilizerNeed();
        this.waterLimit = e.getWaterLimit();
        this.fertilizerLimit = e.getFertilizerLimit();
        this.produceMax = e.getProduceMax();
        this.produceMin = e.getProduceMin();
        this.baseSellingPrice = e.getBaseSellingPrice();
        this.expYield = e.getExpYield();
        this.cost = e.getCost();
        this.timesWatered = 0;
        this.timesFertilized = 0;
    }
    
    /**
     * Decrements the harvestTime
     */
    public void update(){
        this.harvestTime--;
    }
    /**
     * Increments timesWatered if it has not execeeded the limit
     * @param bonusWater  additional water limit
     */
    public void water(int bonusWater){
        if(this.timesWatered < (this.waterLimit + bonusWater))
            this.timesWatered++;
    }
    /**
     * Increments timesFertilzied if it has not exceeded the limit
     * @param bonusFertilizer additional fertilizer limit
     */
    public void fertilize(int bonusFertilizer){
        if(this.timesFertilized < (this.fertilizerLimit + bonusFertilizer))
            this.timesFertilized++;
    }

    /**
     * Checks if the plant is harvestable or not.
     * 
     * @return true if harvestable
     *         false otherwise
     */
    public boolean isHarvestable(){
        if(this.harvestTime == 0 && this.timesFertilized >= this.fertilizerNeed && this.timesWatered >= this.waterNeed)
            return true;
        return false;
    }
    /**
     * Displays timesWatered and timesFertilized
     * Also displays harvestTime if the plant is not withered due to not being harvested
     */
    public void displayStats(){
        System.out.println("Times Watered: " + this.timesWatered);
        System.out.println("Times Fertilized: " + this.timesFertilized);
        if(this.harvestTime >= 0){
            System.out.println("Days until harvest: " + this.harvestTime);
        }
    }
    /**
     * Returns the name of the seed
     * @return name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Returns crop type
     * @return cropType
     */
    public String getCropType(){
        return this.cropType;
    }
    /**
     * Returns the cost of planting the seed
     * @return cost
     */
    public int getCost(){
        return this.cost;
    }
    /**
     * Returns the expYield of the seed
     * @return expYield
     */
    public double getExpYield(){
        return this.expYield;
    }
    /**
     * Returns the number of days until harvest
     * @return harvestTime
     */
    public int getHarvestTime(){
        return this.harvestTime;
    }
    /**
     * Returns the minimum water the seed needs to grow
     * @return waterNeed
     */
    public int getWaterNeed(){
        return this.waterNeed;
    }
    /**
     * Returns the minimum fertilizer the seed needs to grow
     * @return fertilizerNeed
     */
    public int getFertilizerNeed(){
        return this.fertilizerNeed;
    }
    /**
     * Returns the max amount of water the seed can accept
     * @return waterLimit
     */
    public int getWaterLimit(){
        return this.waterLimit;
    }
    /**
     * Returns the max amount of fertilizer the seed can accept
     * @return fertilizerLimit
     */
    public int getFertilizerLimit(){
        return this.fertilizerLimit;
    }
    /**
     * Returns the minimum amount of produces the seed
     * can produce when harvested
     * @return produceMin
     */
    public int getProduceMin(){
        return this.produceMin;
    }
    /**
     * Returns the max amount of produces the seed
     * can produce when harvested
     * @return produceMax
     */
    public int getProduceMax(){
        return this.produceMax;
    }
    /**
     * Returns the base selling price of one produce of the seed
     * @return baseSellingPrice
     */
    public int getBaseSellingPrice(){
        return this.baseSellingPrice;
    }
    /**
     * Returns the amount of times the seed was watered
     * @return timesWatered
     */
    public int getTimesWatered(){
        return this.timesWatered;
    }
    /**
     * Returns the amount of times the seed was fertilized
     * @return timesFertilized
     */
    public int getTimesFertilized(){
        return this.timesFertilized;
    }
    /**
     * Checks if the plant is dead
     * @return true if seed is dead
     *         false otherwise
     */
    public boolean getWither(){
        if(this.harvestTime < 0)
            return true;
        else if (this.harvestTime == 0 && (this.timesWatered < this.waterNeed || this.timesFertilized < this.fertilizerNeed))
            return true;
        else
            return false;
    }
}
