package seeds;
public class Flower extends Crop implements Harvestable{    
    public Flower(String name){
        super(name);
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
