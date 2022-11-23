package seeds;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Crop {
    
    private final String name;
    private int waterNeed;
    private int fertilizerNeed;
    private int waterLimit;
    private int fertilizerLimit;
    private int harvestTime;
    private int baseSellingPrice;
    private int cost;
    private double expYield;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private boolean dead = false;

    public Crop(String name){
        this.name = name;
    }
    //higher level object will call getDead and getHarvestTime then harvest
    public void update(){
        this.harvestTime--;

        if(harvestTime == 0){
            if(timesWatered < waterNeed || timesFertilized < fertilizerNeed){
                this.dead = true;
            }
        }
        else if(harvestTime < 0){
            this.dead = true;
        }
    }

    public void water(){
        timesWatered++;
    }

    public void fertilize(){
        timesFertilized++;
    }

    public double getWaterBonus(double harvestTotal, int waterBonus){
        int newLimit = waterLimit + waterBonus;
        if(timesWatered > newLimit){
            return harvestTotal * 0.2 * (newLimit-1);
        }
        return harvestTotal * 0.2 * (timesWatered -1);
    }

    public double getFertBonus(double harvestTotal, int bonusFert){
        int newLimit = fertilizerLimit + bonusFert;
        if(timesFertilized > newLimit){
            return harvestTotal * 0.5 * newLimit;
        }
        return harvestTotal * 0.5 * timesFertilized;
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

    public int getCost() {
        return this.cost;
    }

    public double getExpYield() {
        return this.expYield;
    }

    public int getTimesWatered() {
        return this.timesWatered;
    }

    public int getTimesFertilized() {
        return this.timesFertilized;
    }

    public int getFertilizerLimit(){
        return this.fertilizerLimit;
    }
    public int getWaterLimit(){
        return this.waterLimit;
    }
    public boolean getDead(){
        return this.dead;
    }

}