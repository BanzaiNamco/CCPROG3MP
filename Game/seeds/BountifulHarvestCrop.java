package seeds;

/**
 * This class introduces a range for number of products
 * a crop can produce.
 */
public abstract class BountifulHarvestCrop extends Crop{
    private int produceMin;
    private int produceMax;

    /**
     * Constructor for this class.
     * @param name name of the crop.
     * @param waterNeed the minimum amount of water a crop needs to become harvestable.
     * @param fertNeed the minimum amount of fertilizer a crop needs to become harvestable.
     * @param harvestTime the amount of days before the crop can be harvested.
     * @param bsp the base selling price of the crop.
     * @param cost the cost of planting the crop.
     * @param expYield the amount of exp the player will gain for harvesting the crop.
     * @param min the minimum amount of products the crop can make on harvest day.
     * @param max the maxmimum amount of products the crop can make on harvest day.
     */
    public BountifulHarvestCrop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield, int min, int max) {
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
        produceMin = min;
        produceMax = max;
    }
    
    /**
     * Gets the minimum amount of products the crop can produce on harvest day.
     * @return the mminimum amount of products the crop can produce on harvest day.
     */
    public int getProduceMin(){
        return produceMin;
    }

    /**
     * Gets the maximum amount of products the crop can produce on harvest day.
     * @return the maximum amount of products the crop can produce on harvest day.
     */
    public int getProduceMax(){
        return produceMax;
    }
}
