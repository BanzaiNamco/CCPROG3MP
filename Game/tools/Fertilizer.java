package tools;

import farm.*;

public class Fertilizer extends Tool implements Useable {

    public Fertilizer(double useCost, double exp) {
        super(useCost, exp);
    }

    /**
     * Checks if tile has a crop object and fertilizes that crop if present.
     * @param tile is the tile with a crop to be fertilized
     * @return true if crop was fertilized, false if no crop could be found
     */
    @Override
    public boolean use(Tile tile) {
        if(tile.getCrop() != null){
            tile.getCrop().fertilize();
            return true;        
        }
        return false;
    }
    
}
