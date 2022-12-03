package tools;
import farm.*;

public class Shovel extends Tool{

    public Shovel(double useCost, double exp) {
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        if(tile.getCrop() != null){
            boolean a = tile.getCrop().getDead();
            tile.resetTile();
            if(!a)
                return true;
        }
        return false;
    }
    
}
