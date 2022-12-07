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
     * Updates
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

    public void water(){
        timesWatered++;
    }

    public void fertilize(){
        timesFertilized++;
    }

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

    public boolean changePlowStatus(){
        if(!plowed && plant == null && !rock){
            this.plowed = true;
            return true;
        }
        return false;
    }

    public void resetTile(){
        plant = null;
        rock = false;
        plowed = false;
        timesWatered = 0;
        timesFertilized = 0;
        deadPlant = false;
        numOfProduce = 0;
    }

    public Crop getCrop(){
        return this.plant;
    }

    public boolean getRock(){
        return this.rock;
    }

    public boolean getPlowed(){
        return this.plowed;
    }

    public int getTimeTilHarvest(){
        return timeTilHarvest;
    }

    public int getNumOfProduce(){
        return numOfProduce;
    }

    public boolean IsPlantDead(){
        return deadPlant;
    }

    public int getTimesWatered(){
        return timesWatered;
    }

    public int getTimesFertilized(){
        return timesFertilized;
    }

    public boolean isEmpty(){
        if(plant == null && !rock)
            return true;
        return false;
    }
}
