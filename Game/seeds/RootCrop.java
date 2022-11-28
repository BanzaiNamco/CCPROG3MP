package seeds;

import java.util.concurrent.ThreadLocalRandom;

public class RootCrop extends Crop implements BountifulHarvest{
    private int produceMin;
    private int produceMax;
    
    public RootCrop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield, int min, int max){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
        produceMin = min;
        produceMax = max;
    }
    public RootCrop(RootCrop rtCrop){
        super(rtCrop);
        produceMin = rtCrop.getProduceMin();
        produceMax = rtCrop.getProduceMax();
    }

    @Override
    public int getRandomProduce() {
        return ThreadLocalRandom.current().nextInt(produceMin, produceMax + 1);
    }

    public int getProduceMin(){
        return produceMin;
    }

    public int getProduceMax(){
        return produceMax;
    }

}
