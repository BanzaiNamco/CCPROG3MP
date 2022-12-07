package seeds;

public abstract class BountifulHarvestCrop extends Crop{
    private int produceMin;
    private int produceMax;

    public BountifulHarvestCrop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield, int min, int max) {
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
        produceMin = min;
        produceMax = max;
    }
    
    public int getProduceMin(){
        return produceMin;
    }
    public int getProduceMax(){
        return produceMax;
    }
}
