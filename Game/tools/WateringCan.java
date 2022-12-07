package tools;

import farm.*;

public class WateringCan extends Tool{

    public WateringCan(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.getCrop() != null){
            if(!tile.IsPlantDead() || tile.getTimeTilHarvest() != 0){ //if not dead or not harvestable
                tile.water();
                return true;
            }
        }
        return false; 
    }
    
}
