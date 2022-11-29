package tools;

import farm.*;

public class Plow extends Tool{
    public Plow(double useCost, double exp){
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.plow())
            return true;
        return false;
    }
}
