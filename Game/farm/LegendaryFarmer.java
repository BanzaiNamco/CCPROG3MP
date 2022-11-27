package farm;

import seeds.BountifulHarvest;
import seeds.Crop;
import seeds.Flower;

public class LegendaryFarmer extends Player{
    public static final int regFee = 400;
    private final int bonusEarn = 4;
    private final int seedCostReduction = 3;
    private final int bonusWater = 2;
    private final int bonusFert = 1;

    public LegendaryFarmer(Player player) {
        super(player, 400);
    }

    @Override
    protected double getFertilizerBonus(double harvestTotal, int timesFertilized, int fertLimit){
        if(timesFertilized > fertLimit + bonusFert)
            return harvestTotal * 0.5 * (fertLimit + bonusFert);
        return harvestTotal * 0.5 * timesFertilized;
    }   

    @Override
    protected double getWaterBonus(double harvestTotal, int timesWatered, int waterLimit){
        if(timesWatered > waterLimit + bonusWater){
            return harvestTotal * 0.2 * ((waterLimit + bonusWater) - 1);
        }
        return harvestTotal * 0.2 * timesWatered;
    }

    @Override
    protected double getHarvestTotal(Crop crop){
        double harvestTotal = 1;
        if(crop instanceof BountifulHarvest){
            harvestTotal = ((BountifulHarvest) crop).getRandomProduce();
        }
        harvestTotal *= (crop.getBaseSellingPrice() + bonusEarn);
        double waterBonus = getWaterBonus(harvestTotal, crop.getTimesWatered(), crop.getWaterLimit());
        double fertBonus = getFertilizerBonus(harvestTotal, crop.getTimesFertilized(), crop.getFertilizerLimit());
        harvestTotal += waterBonus + fertBonus;
        if(crop instanceof Flower)
            harvestTotal *= 1.1;

        return harvestTotal;
    }
    
    @Override
    public void plant(Crop crop, Tile tile){
        if(tile.plant(crop)){
            useObjectCoins(crop.getCost() - seedCostReduction);
        }
    }
    public static int getLevelReq(){
        return 15;
    }
    public static double getCost(){
        return 400;
    }
}
