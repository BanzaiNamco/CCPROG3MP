package proto;

public class Seed {
    private final String name;
    private int waterNeed;
    private int fertilizerNeed;
    private int waterLimit;
    private int fertilizerLimit;
    private int harvestTime;
    private int baseSellingPrice;
    private int produceMin;
    private int produceMax;
    private int cost;
    private double expYield;


    public Seed(String name, int harvestTime, int waterNeed, int fertNeed, int prodMin, int prodMax,
                    int cost, int bsp, double exp){
        this.name = name;
        this.harvestTime = harvestTime;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertNeed;
        if(waterNeed != 7 && fertNeed < 4){
            this.waterLimit = waterNeed + 1;
            this.fertilizerLimit = fertNeed + 1;
        }
        else{
            this.waterLimit = waterNeed;
            this.fertilizerLimit = fertNeed;
        }
        this.produceMin = prodMin;
        this.cost = cost;
        this.baseSellingPrice = bsp;
        this.expYield = exp;
    }
    public String getName(){
        return this.name;
    }
    public int getCost(){
        return this.cost;
    }
    public double getExpYield(){
        return this.expYield;
    }
    public int getHarvestTIme(){
        return this.harvestTime;
    }
    public int getWaterNeed(){
        return this.waterNeed;
    }
    public int getFertilizerNeed(){
        return this.fertilizerNeed;
    }
    public int getWaterLimit(){
        return this.waterLimit;
    }
    public int getFertilizerLimit(){
        return this.fertilizerLimit;
    }
    public int getProduceMin(){
        return this.produceMin;
    }
    public int getProduceMax(){
        return this.produceMax;
    }
    public int getBaseSellingPrice(){
        return this.baseSellingPrice;
    }
}
