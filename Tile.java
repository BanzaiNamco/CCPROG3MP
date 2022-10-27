public class Tile {
    private boolean isPlowed = false;
    private Seed seed = null;
    private boolean hasRock = false;
    private boolean hasWither = false;
   
    public Tile(){}
    public Tile(boolean rock){
        this.hasRock = rock;
    }

    public void update(){
        this.seed.update();
        if(this.seed.getHarvestTime() < 0)
            this.hasWither = true;
    }
    public void resetTile(){
        this.isPlowed = false;
        this.seed = null;
        this.hasRock = false;
        this.hasWither = false;
    }
    public boolean plow(){
        if( this.seed == null && !this.hasRock && !this.isPlowed && !this.hasWither ){
            this.isPlowed = true;
            return true;
        }
        return false;
    }
    public boolean water(int bonusWater){
        if(this.seed != null && this.isPlowed && !this.hasWither){
            this.seed.water(bonusWater);
            return true;
        }
        return false;
    }
    public boolean fertilize(int bonusFertilizer){
        if(this.seed != null && !this.hasWither && this.isPlowed){
            this.seed.fertilize(bonusFertilizer);
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
        if(this.seed != null && this.hasWither){
            return 0;
        }
        else if(this.seed != null && !this.hasWither){
            this.resetTile();
            return 1;
        }
        else if(this.seed == null || this.hasRock){
            return 2;
        }
        else{
            return 3;
        }
    }
    public boolean plant(Seed seed){
        if(this.isPlowed && this.seed == null && !this.hasRock && !this.hasWither){
            this.seed = new Seed(seed);
            System.out.println("Planted a " + this.seed.getName());
            return true;
        }
        return false;
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
    
    public void showStats(){
        System.out.println("\n");
        if(seed != null){
            System.out.println(seed.getName());
            //if(this.hasWither)
                System.out.println("Wither: " + this.hasWither);
            //else
                this.seed.displayStats();
        }
        else{
            System.out.println("No plant");
            System.out.println("Plow: " + this.isPlowed);
            System.out.println("Rock: " + this.hasRock);
            System.out.println("Wither: " + this.hasWither);
        }
    }
    
    public double getSeedExpYield(){
        return this.seed.getExpYield();
    }
    public Seed getSeed(){
        return this.seed;
    }
}
