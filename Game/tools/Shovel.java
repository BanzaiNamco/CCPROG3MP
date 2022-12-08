package tools;
import farm.*;

/**
 * This is the representation for the shovel tool.
 */
public class Shovel extends Tool{

    /**
     * Creates a new Shovel object
     * @param name name of the tool.
     * @param useCost cost of use of the tool.
     * @param exp exp earned from using the tool.
     */
    public Shovel(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

    /**
     * This method resets a tile if there is no rock on it.
     * @param tile tile to be shoveled.
     * @return true if the tile was reset, false otherwise.
     */
    @Override
    public boolean use(Tile tile) {
        if(!tile.getRock()){
            boolean a = (tile.IsPlantDead() && tile.getCrop() != null);
            tile.resetTile();
            if(a) //plant is dead, return true
                return true; //signals that player should get exp for this 
        }
        return false; //player only uses coins
    }
    
}
