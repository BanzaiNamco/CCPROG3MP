package tools;

import farm.*;

public class WateringCan extends Tool{

    public WateringCan(double useCost, double exp) {
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        tile.getCrop().water();
        return true;
    }
    
}
