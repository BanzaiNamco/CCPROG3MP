package MCO;

public class FarmerType extends Player{
    private int bonusCoin;
    private int seedCostReduce;
    private int bonusWater;
    private int bonusFert;
    private String farmer_type;
    
    public FarmerType(Player a){
        this.farmer_type = "Registered Farmer";
        this.bonusCoin = 1;
        this.seedCostReduce = 1;
        this.bonusWater = 0;
        this.bonusFert = 0;
        super(a);
    }
}