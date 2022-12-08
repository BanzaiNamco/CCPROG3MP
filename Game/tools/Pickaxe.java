package tools;

import farm.Tile;

/**
 * This class represents the Pickaxe tool.
 */
public class Pickaxe extends Tool{

    /**
     * Creates a new Pickaxe object.
     * @param name name of the tool.
     * @param useCost cost of using the tool.
     * @param exp amount of exp earned for using the tool.
     */
    public Pickaxe(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

    /**
     * This method removes the rock from a tile.
     * <p>
     * This method calls {@link farm.Tile#resetTile()} which resets all values and removes the rock.
     * There is no side effect from calling {@link farm.Tile#resetTile()}.
     * 
     * @param tile is the tile where the pickaxe will be used.
     * @return true if rock was found and removed, false otherwise.
     */
    @Override
    public boolean use(Tile tile) {
        if(tile.getRock()){
            tile.resetTile();
            return true;
        }
        return false;
    }
    
}
