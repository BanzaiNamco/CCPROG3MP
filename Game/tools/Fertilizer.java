package tools;

import farm.*;

/**
 * This class represents the Fertlilizer tool and extends from {@link tools.Tool}
 */
public class Fertilizer extends Tool{

    /**
     * Constructor for this class
     * @param name name of the Tool
     * @param useCost cost of using the tool
     * @param exp amount of exp earned from using the tool
     */
    public Fertilizer(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

    /**
     * Checks if tile has a crop object and fertilizes that crop if present.
     * @param tile is the tile with a crop to be fertilized
     * @return true if crop was fertilized, false if no crop could be found
     */
    @Override
    public boolean use(Tile tile) {
        if(tile.getCrop() != null){
            if(!tile.IsPlantDead() | tile.getTimeTilHarvest() != 0){
                tile.fertilize();
                return true;        
            }
        }
        return false;
    }
    
}
