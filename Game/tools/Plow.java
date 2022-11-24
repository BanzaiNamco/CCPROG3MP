package tools;

import farm.*;

public class Plow extends Tool{
    public Plow(double expOnUse, double useCost){
        super(useCost, expOnUse);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.plow())
            return true;
        return false;
    }
}
