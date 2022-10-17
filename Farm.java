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
        this.makeFarm();
    }

    private void makeFarm(){
        for(int i = 0; i < this.plotSize; i++){
            plots.add(new Plot());
        }
    }

    public int getPlotSize(){
        return this.plotSize;
    }

    public ArrayList<Plot> getPlots() {
        return this.plots;
    }

    public void setPlots(ArrayList<Plot> plots) {
        this.plots = plots;
    }

    public int getPlotLength() {
        return this.plotLength;
    }

    public void setPlotLength(int plotLength) {
        this.plotLength = plotLength;
    }

    public int getPlotWidth() {
        return this.plotWidth;
    }

    public void setPlotWidth(int plotWidth) {
        this.plotWidth = plotWidth;
    }

    
}
