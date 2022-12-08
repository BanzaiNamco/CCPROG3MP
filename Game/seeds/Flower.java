package seeds;
/**
 * This class is the representation of a flower object that extends {@link seeds.Crop}
 */
public class Flower extends Crop{    

    /**
     * Constructor for this class. Simply calls the super
     * @param name name of the crop
     * @param waterNeed the minimum amount of water a crop needs to become harvestable
     * @param fertNeed the minimum amount of fertilizer a crop needs to become harvestable
     * @param harvestTime the amount of days before the crop can be harvested
     * @param bsp the base selling price of the crop
     * @param cost the cost of planting the crop
     * @param expYield the amount of exp the player will gain for harvesting the crop
     */
    public Flower(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
    }
}
