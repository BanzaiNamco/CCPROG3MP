//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles the information of a tool
 */
package tools;

import farm.Tile;

/**
 * This abstract class serves as the base for all tools.
 */
public abstract class Tool {
    private String name;
    private double useCost;
    private double expOnUse;

    /**
     * Creates a new Tool object.
     * @param name name of the tool
     * @param useCost cost of using the tool
     * @param exp exp gained from using the tool
     */
    protected Tool(String name, double useCost, double exp){
        this.name = name;
        this.expOnUse = exp;
        this.useCost = useCost;
    }

    /**
     * Gets the exp gained from using the tool
     * @return exp gained from using the tool
     */
    public double getExpOnUse(){
        return this.expOnUse;
    }
    /**
     * Gets the cost of using the tool
     * @return cost of using the tool
     */
    public double getUseCost(){
        return this.useCost;
    }
    /**
     * Gets the name of the tool
     * @return name of the tool
     */
    public String getName(){
        return this.name;
    }

    /**
     * Abstract method for how the tool is supposed to function
     * @param tile tile where the tool will be used on
     * @return true or false, depending on implementation
     */
    public abstract boolean use(Tile tile);
}
