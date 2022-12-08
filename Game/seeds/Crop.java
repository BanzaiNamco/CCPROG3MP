package seeds;

/**
 * This abstract class is a representation of a Crop object with no special characteristics.
 */
public abstract class Crop {
    
    private final String name;
    private int waterNeed;
    private int fertilizerNeed;
    private int waterLimit;
    private int fertilizerLimit;
    private int harvestTime;
    private int baseSellingPrice;
    private double cost;
    private double expYield;

    /**
     * Constructor for this class. {@link seeds.Crop#waterLimit} and {@link seeds.Crop#fertilizerLimit}
     * are calculated and set here.
     * @param name name of the crop.
     * @param waterNeed the minimum amount of water a crop needs to become harvestable.
     * @param fertNeed the minimum amount of fertilizer a crop needs to become harvestable.
     * @param harvestTime the amount of days before the crop can be harvested.
     * @param bsp the base selling price of the crop.
     * @param cost the cost of planting the crop.
     * @param expYield the amount of exp the player will gain for harvesting the crop.
     */
    public Crop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield){
        this.name = name;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertNeed;
        this.harvestTime = harvestTime;
        this.baseSellingPrice = bsp;
        this.cost = cost;
        this.expYield = expYield;
        if(waterNeed < 7)
            this.waterLimit = waterNeed + 1;
        else  
            this.waterLimit = waterNeed;

        if(fertNeed < 4)
            this.fertilizerLimit = fertNeed + 1;
        else
            this.fertilizerLimit = fertNeed;    
    }

    /**
     * Gets the name of the crop.
     * @return the name of the crop.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Gets the amount of days before the crop can be harvested.
     * @return the amount of days before the crop can be harvested.
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }
    /**
     * Gets the base selling price of a crop.
     * @return the base selling price of a crop.
     */
    public int getBaseSellingPrice() {
        return this.baseSellingPrice;
    }
    /**
     * Gets the cost of planting the crop.
     * @return the cost of planting the crop.
     */
    public double getCost() {
        return this.cost;
    }
    /**
     * Gets the exp yield from harvesting the crop.
     * @return the exp yield from harvesting the crop.
     */
    public double getExpYield() {
        return this.expYield;
    }
    /**
     * Gets the limit of the number of times the crop can be fertilized.
     * @return the limit of the number of times the crop can be fertilized.
     */
    public int getFertilizerLimit(){
        return this.fertilizerLimit;
    }
    /**
     * Gets the limit of the number of times the crop can be watered.
     * @return the limit of the number of times the crop can be watered.
     */
    public int getWaterLimit(){
        return this.waterLimit;
    }
    /**
     * Gets the minimum amount of water the crop needs to become harvestable.
     * @return minimum amount of water the crop needs to become harvestable.
     */
    public int getWaterNeed(){
        return this.waterNeed;
    }
    /**
     * Gets the minimum amount of fertilizer the crop needs to become harvestable.
     * @return the minimum amount of fertilizer the crop needs to become harvestable.
     */
    public int getFertilizerNeed(){
        return this.fertilizerNeed;
    }
}
