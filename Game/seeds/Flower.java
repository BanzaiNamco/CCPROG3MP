package seeds;
public class Flower extends Crop{    
    public Flower(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
    }
    public Flower(Flower flower){
        super(flower);
    }
}
