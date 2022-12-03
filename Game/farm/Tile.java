package farm;

import seeds.*;

public class Tile {
    private Crop plant = null;
    private boolean rock = false;
    private boolean plowed = false;

    public Tile(int rock){
        if(rock == 1)
            this.rock = true;
    }

    public boolean plant(Crop seed){
        if(plant!=null || rock || !plowed){
            System.out.println("Error planting seed");//TODO remove this 
            return false;
        }

        this.plant = seed;
        return true;
    }

    public boolean plow(){
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
}
