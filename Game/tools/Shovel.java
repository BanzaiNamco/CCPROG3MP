package tools;
import farm.*;

public class Shovel extends Tool{

    public Shovel(String name, double useCost, double exp) {
        super(name, useCost, exp);
    }

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
