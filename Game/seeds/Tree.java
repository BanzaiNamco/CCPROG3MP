package seeds;

import java.util.concurrent.ThreadLocalRandom;

public class Tree extends Crop implements BountifulHarvest{
    private int produceMin;
    private int produceMax;

    public Tree(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield, int min, int max){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
        produceMin = min;
        produceMax = max;
    }
    public Tree(Tree tree){
        super(tree);
        produceMin = tree.getProduceMin();
        produceMax = tree.getProduceMax();
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
