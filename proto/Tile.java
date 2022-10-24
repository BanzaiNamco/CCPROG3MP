package proto;

public class Tile {
    private boolean isPlowed = false;
    private boolean hasPlant = false;
    private Seed seed = null;
    private boolean hasRock = false;
    private boolean hasWither = false;
    private int timesWatered = 0;
    private int timesAddedFertilizer = 0;
    private int daysOccupied = 0;
    
    public void addWater(){
        this.timesWatered++;
    }
    public void addFert(){
        this.timesAddedFertilizer++;
    }
    public void removeCrop(){
        this.seed = null;
        this.hasPlant = false;
        this.isPlowed = false; ///maybe not
        this.timesWatered = 0;
        this.timesAddedFertilizer = 0;
    }
    public void resetTile(){
        this.isPlowed = false;
        this.hasPlant = false;
        this.seed = null;
        this.hasRock = false;
        this.hasWither = false;
        this.timesWatered = 0;
        this.timesAddedFertilizer = 0;
        this.daysOccupied = 0;
    }
    public void update(){
        this.daysOccupied++;
        if(this.daysOccupied > this.seed.getHarvestTIme()){
            this.hasWither = true;
        }
    }
    
    public Seed getSeed(){
        return this.seed;
    }
    public boolean getHasPlant(){
        return this.hasPlant;
    }
    public boolean getIsPlowed(){
        return this.isPlowed;
    }
    public boolean getHasRock(){
        return this.hasRock;
    }
    public boolean getHasWithered(){
        return this.hasWither;
    }
    public int getTimesWatered(){
        return this.timesWatered;
    }
    public int getTimesAddedFertilizer(){
        return this.timesAddedFertilizer;
    } 
    public int getDaysOccupied(){
        return this.daysOccupied;
    }

    public void setCrop(Seed seed){
        this.seed = seed;
    }
    public void setIsPlowed(boolean isPlowed){
        this.isPlowed = isPlowed;
    }
    public void setHasRock(boolean hasRock){
        this.hasRock = hasRock;
    }
    public void setHasWither(boolean hasWither){
        this.hasWither = hasWither;
    }
    public void setHasPlant(boolean hasPlant){
        this.hasPlant = hasPlant;
    }
}
