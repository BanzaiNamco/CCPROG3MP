package farm;

import seeds.BountifulHarvest;
import seeds.Crop;
import seeds.Flower;

public class RegisteredFarmer extends Player{
    private final int bonusEarn = 1;
    private final int seedCostReduction = 1;

    public RegisteredFarmer(Player farmer){
        super(farmer);
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
