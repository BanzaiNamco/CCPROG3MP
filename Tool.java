///This class handles all relevant information on each tool
//Functionality for this class is not implemented as the use of each tool is a
//case by case basis

public class Tool {
    private int useCost;
    private double expOnUse;
    private String name;

    public Tool(String name, int useCost, double exp){
        this.name = name;
        this.expOnUse = exp;
        this.useCost = useCost;
    }

    /* This method returns expOnUse 
     */
    public double getExpOnUse(){
        return this.expOnUse;
    }
    /* This method returns useCost
     */
    public int getUseCost(){
        return this.useCost;
    }
    /*This method returns name*/
    public String getName(){
        return this.name;
    }
}
