package proto;

public class Tools {
    private int useCost;
    private double expOnUse;
    private String name;

    public Tools(String name, double exp, int useCost){
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
