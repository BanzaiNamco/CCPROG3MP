package proto;
import java.util.ArrayList;

public class Farm {
    ArrayList<Plot> plots = new ArrayList<Plot>();
    private int plotLength;
    private int plotWidth;
    private int plotSize;
    
    public Farm(int plotL, int plotW){
        this.plotLength = plotL;
        this.plotWidth = plotW;
        this.plotSize = plotL * plotW;
        for(int i = 0; i < this.plotSize; i++){
            plots.add(new Plot());
        }
    }

    public Tile
    public int getPlotSize(){
        return this.plotSize;
    }
    public ArrayList<Plot> getAllPlots() {
        return this.plots;
    }
    public int getPlotLength() {
        return this.plotLength;
    }
    public int getPlotWidth() {
        return this.plotWidth;
    }
}
