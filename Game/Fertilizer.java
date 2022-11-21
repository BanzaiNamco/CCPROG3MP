public class Fertilizer extends Tool implements Useable {

    public Fertilizer(double useCost, double exp) {
        super(useCost, exp);
    }

    @Override
    public boolean use(Tile tile) {
        tile.getCrop().fertilize();
        return true;        
    }
    
}
