package proto;

public class RootCrop extends Seeds{
    private int waterNeeds;
    private int fertNeeds;
    private int waterBonus;
    private int fertBonus;
    public RootCrop(String name, double exp, int cost, int harvestTime, int waterNeeds, int fertNeeds){
        super(name, exp, cost, harvestTime);
        this.waterNeeds = waterNeeds;
        this.fertNeeds = fertNeeds;
        
        if(this.waterNeeds < 7){
            this.waterBonus = this.waterNeeds + 1;
        }
        else
            this.waterBonus = this.waterNeeds;

        if(this.fertNeeds < 4){
            this.fertBonus = this.fertNeeds + 1;
        }
        else
            this.fertBonus = this.fertNeeds;
    }

    public int getWaterBonus(){
        return this.waterBonus;
    }
    public int getFertBonus(){
        return this.fertBonus;
    }
}
