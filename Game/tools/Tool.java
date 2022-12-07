//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles the information of a tool
 */
package tools;

import farm.Tile;

public abstract class Tool {
    private String name;
    private double useCost;
    private double expOnUse;

    public Tool(String name, double useCost, double exp){
        this.name = name;
        this.expOnUse = exp;
        this.useCost = useCost;
    }

    /** 
     * This method returns expOnUse 
     */
    public double getExpOnUse(){
        return this.expOnUse;
    }
    /**
     * This method returns useCost
     */
    public double getUseCost(){
        return this.useCost;
    }
    public String getName(){
        return this.name;
    }

    public abstract boolean use(Tile tile);
}
