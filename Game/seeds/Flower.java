package seeds;
public class Flower extends Crop implements Harvestable{    
    public Flower(String name, int waterNeed, int fertNeed, int harvestTime, int bsp, double cost, double expYield){
        super(name, waterNeed, fertNeed, harvestTime, bsp, cost, expYield);
    }
    public Flower(Flower flower){
        super(flower);
    }

    @Override
    public double getHarvestTotal(int bonusEarn, int bonusWater, int bonusFert) {
        double harvest = getBaseSellingPrice() + bonusEarn;
        double waterBonus = getWaterBonus(harvest, bonusWater);
        double fertBonus = getFertBonus(harvest, bonusFert);
        harvest += waterBonus + fertBonus;
        return harvest * 1.1;
    }
    
}
