package farm;

import java.util.concurrent.ThreadLocalRandom;

import seeds.*;

/**
 * This class represents a tile in the game.
 * <p>
 * The variables that represent the life cycle of a plant
 * are held here.
 */
public class Tile {
    private Crop plant = null;
    private boolean rock = false;
    private boolean plowed = false;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private int timeTilHarvest;
    private int numOfProduce = 0;
    private boolean deadPlant = false;

    /**
     * Constructor that generates a new tile.
     * @param rock integer representation of the presence of rock. The tile status is changed adjusted on this variable
     */
    public Tile(int rock){
        if(rock == 1)
            this.rock = true;
    }
    /**
     * This method updates the Tile's plant data depending on the status of the plant.
     * <p>
     * If a plant is present, its harvest time will be decremented.
     * Then if the plant is on its harvest day, the code will check if the minimum requirements for that plant
     * have been reached. It declares the plant as dead if the requirements have not been met. Otherwise,
     * the amount produced by the plant will be set to 1 or a random number in a range if the plant is {@link seeds.BountifulHarvestCrop}.
     * The plant is also declared dead if its harvest day has passed.
     */
    public void update(){
        if(plant != null){
            this.timeTilHarvest--;

            if(timeTilHarvest == 0){
                if(timesWatered < plant.getWaterNeed() || timesFertilized < plant.getFertilizerNeed()){
                    deadPlant = true;
                }
                else{
                    if(plant instanceof BountifulHarvestCrop)
                        numOfProduce = ThreadLocalRandom.current().nextInt(((BountifulHarvestCrop)plant).getProduceMin(), ((BountifulHarvestCrop) plant).getProduceMax() + 1);
                    else
                        numOfProduce = 1;
                }
            }
            else if(timeTilHarvest < 0){
                deadPlant = true;
            }
        }
    }

    /**
     * This method increments timesWatered
     */
    public void water(){
        timesWatered++;
    }

    /**
     * This method increments timesFertilized
     */
    public void fertilize(){
        timesFertilized++;
    }

    /**
     * This method works similarly to a setter for {@link farm.Tile#plant}
     * <p>
     * If the tile has not been plowed, has a rock, or already has a plant, planting will not happen.
     * Otherwise, {@link farm.Tile#plant} is set to seed and the corresponding plant variables in this class
     * are adjusted.
     * @param seed the {@link seeds.Crop} object to be planted into Tile
     * @return false if the Tile is not plowed, has a rock, or already has a plant. Otherwise true.
     */
    public boolean addCrop(Crop seed){
        if(plant!=null || rock || !plowed){
            return false;
        }
        this.plant = seed;
        timeTilHarvest = seed.getHarvestTime();
        timesFertilized = 0;
        timesWatered = 0;
        return true;
    }

    /**
     * This method checks if the Tile can be plowed. This means
     * the tile should not be plowed and have no plant or rock.
     * If the check returns true, then {@link farm.Tile#plowed} is set to true.
     * @return true if tile was plowed, false otherwise
     */
    public boolean changePlowStatus(){
        if(!plowed && plant == null && !rock){
            this.plowed = true;
            return true;
        }
        return false;
    }

    /**
     * This method resets all variables to its default values.
     * {@link farm.Tile#rock} is always set to false.
     */
    public void resetTile(){
        plant = null;
        rock = false;
        plowed = false;
        timesWatered = 0;
        timesFertilized = 0;
        deadPlant = false;
        numOfProduce = 0;
    }

    /**
     * Gets the {@link seeds.Crop} object stored in this class
     * @return {@link seeds.Crop} object stored in this class
     */
    public Crop getCrop(){
        return this.plant;
    }

    /**
     * Gets the presence status of the rock in the tile
     * @return the presence status of the rock in the tile
     */
    public boolean getRock(){
        return this.rock;
    }

    /**
     * Gets the tile plowed status
     * @return tile plowed status
     */
    public boolean getPlowed(){
        return this.plowed;
    }

    /**
     * Gets the number of days until the plant can be harvested
     * @return number of days until the plant can be harvested
     */
    public int getTimeTilHarvest(){
        return timeTilHarvest;
    }

    /**
     * Gets the number of products the plant was able to produce
     * @return the number of products the plant was able to produce
     */
    public int getNumOfProduce(){
        return numOfProduce;
    }

    /**
     * Gets the plant dead status
     * @return the plant dead status
     */
    public boolean IsPlantDead(){
        return deadPlant;
    }

    /**
     * Gets the number of times the tile has been watered
     * @return the number of times the tile has been watered
     */
    public int getTimesWatered(){
        return timesWatered;
    }

    /**
     * Gets the number of times the tile has been fertilized
     * @return the number of times the tile has been fertilized
     */
    public int getTimesFertilized(){
        return timesFertilized;
    }

    /**
     * Checks if the tile does not have a plant or rock
     * @return true if there is not plant or rock. False otherwise
     */
    public boolean isEmpty(){
        if(plant == null && !rock)
            return true;
        return false;
    }
}
