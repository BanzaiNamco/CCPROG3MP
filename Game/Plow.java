public class Plow extends Tool implements Useable{
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
