package farm;

import seeds.BountifulHarvest;
import seeds.Crop;
import seeds.Flower;

public class RegisteredFarmer extends Player implements Upgradeable{
    private final int bonusEarn = 1;
    private final int seedCostReduction = 1;

    public RegisteredFarmer(Player farmer){
        super(farmer, 200);
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
    public boolean plant(Crop crop, Tile tile){
        if(tile.plant(crop)){
            useObjectCoins(crop.getCost() - seedCostReduction);
            return true;
        }
        return false;
    }

    public static int getLevelReq(){
        return 5;
    }
    public static double getCost(){
        return 200;
    }
}
