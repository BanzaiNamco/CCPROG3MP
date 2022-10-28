public class Seed {
    private final String name;
    private final String cropType;
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
    private int timesWatered = 0;
    private int timesFertilized = 0;


    public Seed(String name, String type, int harvestTime, int waterNeed, int fertNeed, int prodMin, int prodMax,
                    int cost, int bsp, double exp){
        this.name = name;
        this.cropType = type;
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
        this.produceMax = prodMax;
        this.cost = cost;
        this.baseSellingPrice = bsp;
        this.expYield = exp;
    }
    public Seed(Seed e){
        this.name = e.getName();
        this.cropType = e.getCropType();
        this.harvestTime = e.getHarvestTime();
        this.waterNeed = e.getWaterNeed();
        this.fertilizerNeed = e.getFertilizerNeed();
        this.waterLimit = e.getWaterLimit();
        this.fertilizerLimit = e.getFertilizerLimit();
        this.produceMax = e.getProduceMax();
        this.produceMin = e.getProduceMin();
        this.baseSellingPrice = e.getBaseSellingPrice();
        this.expYield = e.getExpYield();
        this.timesWatered = 0;
        this.timesFertilized = 0;
    }
    
    public void update(){
        this.harvestTime--;
    }
    public void water(int bonusWater){
        if(this.timesWatered < (this.waterLimit + bonusWater))
            this.timesWatered++;
    }
    public void fertilize(int bonusFertilizer){
        if(this.timesFertilized < (this.fertilizerLimit + bonusFertilizer))
            this.timesFertilized++;
    }
    public boolean getHarvestable(){
        if (this.harvestTime == 0 && this.timesFertilized >= this.fertilizerNeed && this.timesWatered >= this.waterNeed)
            return true;
        return false;
    }
    public void displayStats(){
        System.out.println("Times Watered: " + this.timesWatered);
        System.out.println("Times Fertilized: " + this.timesFertilized);
        if(this.harvestTime >= 0){
            System.out.println("Days until harvest: " + this.harvestTime);
        }
    }
    public String getName(){
        return this.name;
    }
    public String getCropType(){
        return this.cropType;
    }
    public int getCost(){
        return this.cost;
    }
    public double getExpYield(){
        return this.expYield;
    }
    public int getHarvestTime(){
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
    public int getTimesWatered(){
        return this.timesWatered;
    }
    public int getTimesFertilized(){
        return this.timesFertilized;
    }
    public boolean getStatus(){
        if(this.harvestTime < 0)
            return true;
        else if (this.harvestTime == 0 && (this.timesWatered < this.waterNeed || this.timesFertilized < this.fertilizerNeed))
            return true;
        else
            return false;
    }
}
