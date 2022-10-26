package proto;

public class Tool {
    private int useCost;
    private double expOnUse;
    private String name;

    public Tool(String name, int useCost, double exp){
        this.name = name;
        this.expOnUse = exp;
        this.useCost = useCost;
    }

    public double getExpOnUse(){
        return this.expOnUse;
    }
    public int getUseCost(){
        return this.useCost;
    }
    public String getName(){
        return this.name;
    }
}
