package seeds;

import java.util.concurrent.ThreadLocalRandom;

public class RootCrop extends Crop implements BountifulHarvest{
    private int produceMin;
    private int produceMax;
    
    public RootCrop(String name, int min, int max){
        super(name);
        produceMin = min;
        produceMax = max;
    }

    @Override
    public int getRandomProduce() {
        return ThreadLocalRandom.current().nextInt(produceMin, produceMax + 1);
    }


}
