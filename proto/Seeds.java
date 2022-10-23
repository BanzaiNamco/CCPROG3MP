package proto;

public class Seeds {
    private boolean withered;
    private int timesWatered;
    private int harvestTime;
    private int timesFertilizer;
    private double expYield;
    private int cost;
    private String name;
    private int daysPassed; //not sure

    public Seeds(String name, double exp, int cost, int harvestTime){
        this.withered = false;
        this.timesWatered = 0;
        this.timesFertilizer = 0;
        this.daysPassed = 0;
        this.expYield = exp;
        this.name = name;
        this.cost = cost;
        this.harvestTime = harvestTime;
    }
    public void addDayPassed(){
        this.daysPassed+=1;
    }
    public void setWithered(boolean withered){
        this.withered = withered;
    }
    public void addWater(){
        this.timesWatered += 1;
    }
    public int getDaysPassed(){
        return this.daysPassed;
    }
    public String getName(){
        return this.name;
    }
    public int getCost(){
        return this.cost;
    }
    public boolean getWithered(){
        return this.withered;
    }
    public double getExpYield(){
        return this.expYield;
    }
    public int getHarvestTime(){
        return this.harvestTime;
    }
    public int getTimesWatered(){
        return this.timesWatered;
    }
    public int getTimesFertilizer(){
        return this.timesFertilizer;
    }



}
