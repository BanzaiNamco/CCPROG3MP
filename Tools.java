package proto;

public class Tools {
    private double expGain;
    private int useCost;
    private String name;

    public Tools(String name, double expGain, int useCost){
        this.name = name;
        this.expGain = expGain;
        this.useCost = useCost;
    }

    public double getExpGain(){
        return this.expGain;
    }
    public int getuseCost(){
        return this.useCost;
    }
    public String getName(){
        return this.name;
    }

    protected void setExpGain(double expGain){
        this.expGain = expGain;
    }

    protected void setUseCost(int useCost){
        this.useCost = useCost;
    }
}
