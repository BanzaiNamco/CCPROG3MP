package tools;

import farm.Tile;

public class Pickaxe extends Tool{

    public Pickaxe(double useCost, double exp) {
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.getRock()){
            tile.resetTile();
            return true;
        }
        return false;
    }
    
}
