package tools;

import farm.*;

public class Plow extends Tool{
    public Plow(String name, double useCost, double exp){
        super(name, useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.changePlowStatus())
            return true;
        return false;
    }
}
