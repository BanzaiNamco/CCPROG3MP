//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles the information of a tool
 */
package tools;

public abstract class Tool {
    private double useCost;
    private double expOnUse;

    public Tool(double useCost, double exp){
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
}
