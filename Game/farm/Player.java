package farm;

import seeds.*;
import tools.*;


public abstract class Player {
    private final String name;
    private int level;
    private double exp;
    private double objectCoins;

    public Player(String name){
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.exp = 0;
    }
    protected Player(Player player, int objectCoins){
        this.name = player.getName();
        this.objectCoins = player.getObjectCoins() - objectCoins;
        this.level = player.getLevel();
        this.exp = player.getExp();
    }
    
    public void update(){
        if (this.level <= Math.floor(exp/100) && this.exp > (this.level * 100) + 99){ //probably a better way to do this tbh
            this.level++;
        }
    }

    protected double getHarvestTotal(Crop crop){
        double harvestTotal = 1;
        if(crop instanceof BountifulHarvest){
            harvestTotal = ((BountifulHarvest) crop).getRandomProduce();
        }
        harvestTotal *= (crop.getBaseSellingPrice());
        double waterBonus = getWaterBonus(harvestTotal, crop.getTimesWatered(), crop.getWaterLimit());
        double fertBonus = getFertilizerBonus(harvestTotal, crop.getTimesFertilized(), crop.getFertilizerLimit());
        harvestTotal += waterBonus + fertBonus;
        if(crop instanceof Flower)
            harvestTotal *= 1.1;

        return harvestTotal;
    }
    
    public void plant(Crop crop, Tile tile){
        if(tile.plant(crop)){
            useObjectCoins(crop.getCost());
        }
    }

    protected double getFertilizerBonus(double harvestTotal, int timesFertilized, int fertLimit) {
        if(timesFertilized > fertLimit){
            return harvestTotal * 0.5 * fertLimit;
        }
        return harvestTotal * 0.5 * timesFertilized;
    }

    protected double getWaterBonus(double harvestTotal, int timesWatered, int waterLimit){
        if(timesWatered > waterLimit){
            return harvestTotal * 0.2 * (waterLimit-1);
        }
        return harvestTotal * 0.2 * timesWatered;
    }

    public boolean harvestCrop(Tile tile){
        if(tile.getCrop() != null){
            if(!tile.getCrop().getDead() && tile.getCrop().getHarvestTime() == 0){
                double harvestTotal = getHarvestTotal(tile.getCrop());
                addObjectCoins(harvestTotal);
                gainExp(tile.getCrop().getExpYield());
                return true;
            }
        }
        return false;
    }
    
    public void useTool(Tool tool, Tile tile){
        if(tool instanceof Useable){
            if(((Useable)tool).use(tile)){
                gainExp(tool.getExpOnUse());
                useObjectCoins(tool.getUseCost());
            }
            else{
                if(tool instanceof Shovel){
                    useObjectCoins(tool.getUseCost());
                }
            }
        }
    }

    protected void addObjectCoins(double coins){
        objectCoins += coins;
    }

    protected void useObjectCoins(double coins){
        objectCoins -= coins;
    }

    protected void gainExp(double exp){
        exp += exp;
    }

    public String getName() {
        return this.name;
    }

    public double getObjectCoins() {
        return this.objectCoins;
    }

    public int getLevel() {
        return this.level;
    }

    public double getExp(){
        return this.exp;
    }

}
