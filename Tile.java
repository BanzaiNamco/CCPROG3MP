import java.util.concurrent.ThreadLocalRandom;

public class Tile {
    private boolean isPlowed = false;
    private boolean hasPlant = false;
    private Seed seed = null;
    private boolean hasRock = false;
    private boolean hasWither = false;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private int daysOccupied = 0;
    
    public Tile(){}
    public Tile(boolean rock){
        this.hasRock = rock;
    }

    public void resetTile(){
        this.isPlowed = false;
        this.hasPlant = false;
        this.seed = null;
        this.hasRock = false;
        this.hasWither = false;
        this.timesFertilized = 0;
        this.timesWatered = 0;
        this.daysOccupied = 0;
    }
    public void update(){
        if(this.hasPlant)
            this.daysOccupied++;
        if(this.daysOccupied > this.seed.getHarvestTIme()){
            this.hasWither = true;
        }
        else if(this.daysOccupied == this.seed.getHarvestTIme() && this.timesWatered < this.seed.getWaterNeed()
                && this.timesFertilized < this.seed.getFertilizerNeed()){
            this.hasWither = true;
        }
    }
    public int calcHarvestTotal(int bonusEarn){
        int harvestTotal = getRandomProduce() * (this.seed.getBaseSellingPrice() + bonusEarn);
        harvestTotal += getWaterBonus(harvestTotal) + getFertilizerBonus(harvestTotal);

        if(this.seed.getCropType().equals("Flower"))
            harvestTotal *= 1.1;
        return harvestTotal;
    }  
    public double getWaterBonus(int harvestTotal){
        return harvestTotal * 0.2 * (this.timesWatered-1);
    }
    public double getFertilizerBonus(int harvestTotal){
        return harvestTotal * 0.5 * this.timesFertilized;
    }
    
    public boolean plow(){
        if( !this.hasPlant && !this.hasRock && !this.isPlowed && !this.hasWither ){
            this.isPlowed = true;
            return true;
        }
        return false;
    }
    public boolean water(int bonusWater){
        if(this.hasPlant && this.isPlowed && !this.hasWither){
            if(this.timesWatered < this.seed.getWaterLimit() + bonusWater)
                this.timesWatered++;
            return true;
        }
        return false;
    }
    public boolean fertilize(int bonusFertilizer){
        if(this.hasPlant && !this.hasWither && this.isPlowed){
            if(this.timesFertilized < this.seed.getFertilizerLimit() + bonusFertilizer)
                this.timesFertilized++;
            return true;
        }
        return false;
    }
    public boolean pickaxe(){
        if(this.hasRock){
            this.hasRock = false;
            return true;
        }
        return false;
    }
    //return 0 removed wither
    // 1 if removed an alive plant
    // 2 no effect
    // 3 if error
    public int shovel(){
        if(this.hasPlant && this.hasWither){
            return 0;
        }
        else if(this.hasPlant && !this.hasWither){
            this.resetTile();
            return 1;
        }
        else if(!this.isPlowed || this.hasRock){
            return 2;
        }
        else{
            return 3;
        }
    }
    public boolean plant(Seed seed){
        if(this.isPlowed && !this.hasPlant && !this.hasRock && !this.hasWither){
            this.seed = seed;
            this.hasPlant = true;
            System.out.println("Planted a " + this.seed.getName());
            return true;
        }
        return false;
    }
    public boolean requirementsMet(){
        if(this.seed != null)
            if(this.daysOccupied == this.seed.getHarvestTIme())
                if(this.timesFertilized >= this.seed.getFertilizerNeed() && this.timesWatered >= this.seed.getWaterNeed())
                    return true;
        return false;
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
    public int getTimesFertilized(){
        return this.timesFertilized;
    }
    public int getDaysOccupied(){
        return this.daysOccupied;
    }
    
    public void showStats(){
        System.out.println("\n");
        if(seed != null){
            System.out.println(seed.getName());
        }
        else{
            System.out.println("No plant");
        }
        System.out.println("Plow: " + this.isPlowed);
        System.out.println("Rock: " + this.hasRock);
        System.out.println("Wither: " + this.hasWither);
        if(seed!= null){
            System.out.println("Times Watered: " + this.timesWatered);
            System.out.println("Times Fertilized " + this.timesFertilized);
            System.out.println("Days until harvest: " + (this.seed.getHarvestTIme() - this.daysOccupied));
        }
    }
    
    public double getSeedExpYield(){
        return this.seed.getExpYield();
    }
    private int getRandomProduce(){
        return ThreadLocalRandom.current().nextInt(this.seed.getProduceMin(), this.seed.getProduceMax() + 1);
    }
}
