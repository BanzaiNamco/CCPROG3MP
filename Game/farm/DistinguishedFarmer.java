package farm;

import seeds.*;

public class DistinguishedFarmer extends Player{
    private final int bonusEarn = 2;
    private final int seedCostReduction = 2;
    private final int bonusWater = 1;

    public DistinguishedFarmer(Player farmer) {
        super(farmer, 300);
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
    
}
