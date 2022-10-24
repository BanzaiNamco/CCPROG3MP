package proto;
import java.util.ArrayList;

public class Farm {
    private ArrayList<Tile> plots = new ArrayList<Tile>();
    private int plotLength;
    private int plotWidth;
    private int plotSize;
    
    public Farm(int plotL, int plotW){
        this.plotLength = plotL;
        this.plotWidth = plotW;
        this.plotSize = plotL * plotW;
        for(int i = 0; i < this.plotSize; i++){
            plots.add(new Tile());
        }
    }
    public boolean canHarvest(int i){
        if(this.plots.get(i).getHasPlant() && !this.plots.get(i).getHasWithered()
        && (this.plots.get(i).getDaysOccupied() == this.plots.get(i).getSeed().getHarvestTIme())){
            return true;
        }
        return false;
    }
    public boolean canPlow(int i){
        if(!this.plots.get(i).getIsPlowed() && !this.plots.get(i).getHasRock()){
            return true;
        }
        return false;
    }
    public boolean canWater(int i){
        if(this.plots.get(i).getHasPlant() && !this.plots.get(i).getHasWithered()
            && this.plots.get(i).getIsPlowed()){
            return true;
        }
        return false;
    }
    public boolean canFertilize(int i){
        if(this.plots.get(i).getHasPlant() && !this.plots.get(i).getHasWithered()
            && this.plots.get(i).getIsPlowed()){
            return true;
        }
        return false;
    }
    public boolean canPickaxe(int i){
        if(this.plots.get(i).getHasRock()){
            return true;
        }
        return false;
    }
    public int canShovel(int i){
        if(!this.plots.get(i).getIsPlowed() || this.plots.get(i).getHasRock()){
            return 0;
        }
        else if(this.plots.get(i).getHasPlant() && !this.plots.get(i).getHasWithered()){
            return 1;
        }
        else{
            return 2;
        }
    }
    public boolean canPlant(int i){
        if(this.plots.get(i).getIsPlowed() && !this.plots.get(i).getHasPlant()
            && !this.plots.get(i).getHasRock() && !this.plots.get(i).getHasWithered()){
                return true;
        }
        return false;
    }



    //getters
    public Tile getPlot(int i){
        return this.plots.get(i);
    }
    public int getPlotSize(){
        return this.plotSize;
    }
    public ArrayList<Tile> getAllPlots() {
        return this.plots;
    }
    public int getPlotLength() {
        return this.plotLength;
    }
    public int getPlotWidth() {
        return this.plotWidth;
    }
}
