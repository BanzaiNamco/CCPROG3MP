//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles the information of a tool
 */

public class Tool {
    private int useCost;
    private double expOnUse;
    private String name;

    public Tool(String name, int useCost, double exp){
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
    public int getUseCost(){
        return this.useCost;
    }
    /**
     * This method returns name
     */
    public String getName(){
        return this.name;
    }
}
