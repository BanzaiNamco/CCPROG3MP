package tools;

import farm.*;

/**
 * This class represents the Watering can tool.
 */
public class WateringCan extends Tool{

    /**
     * Creates a new WateringCan object.
     * @param name name of the tool.
     * @param useCost cost of using the tool.
     * @param exp exp gained form using the tool.
     */
    public WateringCan(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

    /**
     * This method waters the tile if there is a planted crop that is not dead or harvestable.
     * @param tile tile to be watered.
     * @return true if tile was watered, false otherwise.
     */
    @Override
    public boolean use(Tile tile) {
        if(tile.getCrop() != null){
            if(!tile.IsPlantDead() && tile.getTimeTilHarvest() != 0){ //if not dead or not harvestable
                tile.water();
                return true;
            }
        }
        return false; 
    }
    
}