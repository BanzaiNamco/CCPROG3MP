public class WateringCan extends Tool implements Useable{

    public WateringCan(double useCost, double exp) {
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        tile.getCrop().water();
        return true;
    }
    
}
