public class Tree extends Crop implements Harvestable{
    private int produceMin;
    private int produceMax;

    public Tree(String name, int min, int max){
        super(name);
        produceMin = min;
        produceMax = max;
    }

    @Override
    public double getHarvestTotal() {
        double harvest = findRandomProduce(produceMin, produceMax) * (getBaseSellingPrice());
        double waterBonus = getWaterBonus(harvest);
        double fertBonus = getFertBonus(harvest);

        harvest += waterBonus + fertBonus;
        return harvest;
    }

    @Override
    public double getHarvestTotal(int bonusEarn) {
        double harvest = findRandomProduce(produceMin, produceMax) * (getBaseSellingPrice() + bonusEarn);
        double waterBonus = getWaterBonus(harvest);
        double fertBonus = getFertBonus(harvest);

        harvest += waterBonus + fertBonus;
        return harvest;
    }

    @Override
    public double getHarvestTotal(int bonusEarn, int bonusWater, int bonusFert) {
        double harvest = findRandomProduce(produceMin, produceMax) * (getBaseSellingPrice() + bonusEarn);
        double waterBonus = getWaterBonus(harvest, bonusWater);
        double fertBonus = getFertBonus(harvest, bonusFert);

        harvest += waterBonus + fertBonus;
        return harvest;
    }
}
