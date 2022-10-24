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
