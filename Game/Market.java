//Orrin Landon T. Uy ID12111287
/**
 * This class handles the calculations for harvesting
 */
import java.util.concurrent.ThreadLocalRandom;

public class Market {
    /**
     * This method calculates the Total Harvest Price of a plant that is ready for harvesting
     * <p>
     * This method returns the total harvest price for the passed Seed object
     * whether or not the Seed object is ready for harvesting or not
     * 
     * @param seed  Seed object whose total harvest price is being calculated for
     * @param player the player who harvested the seed
     * @return harvestTotal, the total harvesting price of seed
     */
    public boolean findHarvestTotal(Seed seed, Farmer player){
        if(seed.isHarvestable()){
            double harvestTotal = findRandomProduce(seed.getProduceMin(), seed.getProduceMax());
            System.out.println(seed.getName() + " produced " + harvestTotal + " products");
            harvestTotal *= (seed.getBaseSellingPrice()+ player.getBonusEarn());
            harvestTotal +=  findWaterBonus(harvestTotal, seed.getTimesWatered()) + findFertilizerBonus(harvestTotal, seed.getTimesFertilized());

            if(seed.getCropType().equals("Flower"))
                harvestTotal *= 1.1;

            player.gainExp(seed.getExpYield());
            player.gainObjectCoins(harvestTotal);
            System.out.println("Harvest Successful!");
            return true;
        }
        else{
            System.out.println("Harvest unsuceessful");
            return false;
        }
    }
    /**
     * This method returns a random number given a minimum and a maximum
     * 
     * @param min  minimum bound for the random number
     * @param max  maximum bound for the random number
     * @return  random number [min, max]
     */
    private int findRandomProduce(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    /**
     * Returns the bonus objectCoins to be earned from adding water
     * @param harvestTotal  amount to be earned without bonuses
     * @param timesWatered  times the plant was watered
     * @return
     */
    private double findWaterBonus(double harvestTotal, int timesWatered){
        return harvestTotal * 0.2 * (timesWatered-1);
    }
    /**
     * Returns the bonus objectCoins to be earned from adding fertilizer
     * @param harvestTotal  amount to be earned without bonuses
     * @param timesFertilized  times the plant was fertilized
     * @return
     */
    private double findFertilizerBonus(double harvestTotal, int timesFertilized){
        return harvestTotal * 0.5 * timesFertilized;
    }
}
