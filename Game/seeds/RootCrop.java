package seeds;

/**
 * This clas is the representation of Root Crops objects that extends {@link seeds.BountifulHarvestCrop}
 */
public class RootCrop extends BountifulHarvestCrop{

    /**
     * Constructor for this class. Simply calls the super
     * @param name name of the crop
     * @param waterNeed the minimum amount of water a crop needs to become harvestable
     * @param fertNeed the minimum amount of fertilizer a crop needs to become harvestable
     * @param harvestTime the amount of days before the crop can be harvested
     * @param bsp the base selling price of the crop
     * @param cost the cost of planting the crop
     * @param expYield the amount of exp the player will gain for harvesting the crop
     * @param min the minimum amount of products the crop can make on harvest day
     * @param max the maxmimum amount of products the crop can make on harvest day
     */
    public RootCrop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield, int min, int max){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield, min, max);
    }
}
