//Orrin Landon T. Uy S17 ID12111287
/**
 * This class is responsible for all functions for an individual tile in the game
 */

public class Tile {
    private boolean isPlowed = false;
    private Seed seed = null;
    private boolean hasRock = false;
    private boolean hasWither = false;
   
    public Tile(){}
    public Tile(boolean rock){
        this.hasRock = rock;
    }

    /**
     * Calls the seed update() method if seed is present
     * Updates hasWither if the seed is withered
     * @return true if seed is present and has been updated, false otherwise
     */
    public boolean update(){
        if(this.seed != null){
            this.seed.update();

            if(this.seed.getWither()){
                this.hasWither = true;
            }
            return true;
        }
        return false;
    }
    /**
     * Resets all object variables back to default
     */
    public void resetTile(){
        this.isPlowed = false;
        this.seed = null;
        this.hasRock = false;
        this.hasWither = false;
    }
    /**
     * Sets the status of isPlowed to true if the tile can be plowed
     * @return true if tile was plowed, false otherwise
     */
    public boolean plow(){
        if( this.seed == null && !this.hasRock && !this.isPlowed && !this.hasWither ){
            this.isPlowed = true;
            return true;
        }
        return false;
    }
    /**
     * Waters the seed in the tile if present
     * @param bonusWater  bonus water limit
     * @return  true if seed was watered, false otherwise
     */
    public boolean water(int bonusWater){
        if(this.seed != null && this.isPlowed && !this.hasWither){
            this.seed.water(bonusWater);
            return true;
        }
        return false;
    }
    /**
     * Fertilizes the seed in the tile if present
     * @param bonusFertilizer  bonus fertilizer limit
     * @return  true if seed was fertilized, false otherwise
     */
    public boolean fertilize(int bonusFertilizer){
        if(this.seed != null && !this.hasWither && this.isPlowed){
            this.seed.fertilize(bonusFertilizer);
            return true;
        }
        return false;
    }
    /**
     * Removes rock if present
     * @return true if rock has been removed, false otherwise
     */
    public boolean pickaxe(){
        if(this.hasRock){
            this.hasRock = false;
            return true;
        }
        return false;
    }
    /**
     * Calls resetTile() and returns the appropriate int
     * @return 0 if withered seed is removed from tile
     *         1 if healthy plant was removed from tile
     *         2 if nothing happened
     *         3 when unexpected behavior has happened
     */
    public int shovel(){
        if(this.seed != null && this.hasWither){
            this.resetTile();
            return 0;
        }
        else if(this.seed != null && !this.hasWither){
            this.resetTile();
            return 1;
        }
        else if(this.seed == null || this.hasRock){
            if(this.hasRock){
                this.resetTile();
                this.hasRock = true;
            }
            else
                this.resetTile();
            
            return 2;
        }
        else{
            return 3;
        }
    }
    /**
     * Sets the seed in the tile to the passed seed if the tile is plantable
     * @param seed  seed to be planted
     * @return  true if seed is planted
     *          false otherwise
     */
    public boolean plant(Seed seed){
        if(this.isPlowed && this.seed == null && !this.hasRock && !this.hasWither){
            this.seed = seed;
            //insert check for tree here
            System.out.println("Planted a " + this.seed.getName());
            return true;
        }
        return false;
    }
    
    /**
     * Returns the boolean plowed status of the tile
     * @return isPlowed
     */
    public boolean getIsPlowed(){
        return this.isPlowed;
    }
    /**
     * Returns the boolean value of the rock status of the tile
     * @return hasRock
     */
    public boolean getHasRock(){
        return this.hasRock;
    }
    /**
     * Returns the boolean value of the withered status of the seed in the plant
     * @return hasWither
     */
    public boolean getHasWithered(){
        return this.hasWither;
    }
    
    /**
     * Displays the plowing and rock status of the tile if no seed is present
     * Otherwise displays the seed name and calls seed.displayStats() if the seed is not withered
     */
    public void showStats(){
        System.out.println("\n");
        if(seed != null){
            System.out.println(seed.getName());
            if(this.hasWither)
                System.out.println("Wither: " + this.hasWither);
            else
                this.seed.displayStats();
        }
        else{
            System.out.println("No plant");
            System.out.println("Plow: " + this.isPlowed);
            System.out.println("Rock: " + this.hasRock);
        }
    }
    
    /**
     * Returns the seed in the tile
     * @return seed
     */
    public Seed getSeed(){
        return this.seed;
    }
}
