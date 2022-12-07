package seeds;

public abstract class Crop {
    
    private final String name;
    private int waterNeed;
    private int fertilizerNeed;
    private int waterLimit;
    private int fertilizerLimit;
    private int harvestTime;
    private int baseSellingPrice;
    private double cost;
    private double expYield;

    public Crop(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield){
        this.name = name;
        this.waterNeed = waterNeed;
        this.fertilizerNeed = fertNeed;
        this.harvestTime = harvestTime;
        this.baseSellingPrice = bsp;
        this.cost = cost;
        this.expYield = expYield;
        if(waterNeed < 7)
            this.waterLimit = waterNeed + 1;
        else  
            this.waterLimit = waterNeed;

        if(fertNeed < 4)
            this.fertilizerLimit = fertNeed + 1;
        else
            this.fertilizerLimit = fertNeed;    
    }
    public Crop(Crop crop){
        this.name = crop.getName();
        this.waterNeed = crop.getWaterNeed();
        this.fertilizerNeed = crop.getFertilizerNeed();
        this.harvestTime = crop.getHarvestTime();
        this.baseSellingPrice = crop.getBaseSellingPrice();
        this.cost = crop.getCost();
        this.expYield = crop.getExpYield();
        this.waterLimit = crop.getWaterLimit();
        this.fertilizerLimit = crop.getFertilizerLimit();    
    }

    public String getName() {
        return this.name;
    }
    public int getHarvestTime() {
        return this.harvestTime;
    }
    public int getBaseSellingPrice() {
        return this.baseSellingPrice;
    }
    public double getCost() {
        return this.cost;
    }
    public double getExpYield() {
        return this.expYield;
    }
    public int getFertilizerLimit(){
        return this.fertilizerLimit;
    }
    public int getWaterLimit(){
        return this.waterLimit;
    }
    public int getWaterNeed(){
        return this.waterNeed;
    }
    public int getFertilizerNeed(){
        return this.fertilizerNeed;
    }
}
