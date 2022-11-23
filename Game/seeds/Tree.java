package seeds;

import java.util.concurrent.ThreadLocalRandom;

public class Tree extends Crop implements BountifulHarvest{
    private int produceMin;
    private int produceMax;

    public Tree(String name, int min, int max){
        super(name);
        produceMin = min;
        produceMax = max;
    }

    @Override
    public int getRandomProduce() {
        return ThreadLocalRandom.current().nextInt(produceMin, produceMax + 1);
    }

    
}
