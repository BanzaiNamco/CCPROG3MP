package proto;

public class Farmer extends Player{
    private int bonusEarnings;
    private int seedCostReduction;
    private int plusWaterBonusLimit;
    private int plusFertBonusLimit;
    private String type;

    public Farmer(String name){
        super(name);
        this.type = "Farmer";
        this.bonusEarnings = 0;
        this.seedCostReduction = 0;
        this.plusWaterBonusLimit = 0;
        this.plusFertBonusLimit = 0;
    }

    public void Register(){
        if(this.type.equals("Farmer")){
            if(this.getLevel() >= 5 && this.getObjectCoins() >= 200){
                this.type = "Registered Farmer";
                this.bonusEarnings = 1;
                this.seedCostReduction = 1;
                this.minusObjectCoins(200);
            }
            else{
                //not high enough level
            }
        }
        else if(this.type.equals("Registered Farmer")){
            if(this.getLevel() >= 10 && this.getObjectCoins() >= 300){
                this.type = "Distinguished Farmer";
                this.bonusEarnings = 2;
                this.seedCostReduction = 2;
                this.plusWaterBonusLimit = 1;
                this.minusObjectCoins(300);
            }
            else{
                //not allowed
            }
        }
        else if(this.type.equals("Distinguished Farmer")){
            if(this.getLevel() >=15 && this.getObjectCoins() >= 400){
                this.type = "Legendary Farmer";
                this.bonusEarnings = 4;
                this.seedCostReduction = 3;
                this.plusWaterBonusLimit = 2;
                this.plusFertBonusLimit = 1;
                this.minusObjectCoins(400);
            }
        }
        else if (this.type.equals("Legendary Farmer")){
            //bro
        }
        else{
            //error
        }
    }
    public int getBonusEarn(){
        return this.bonusEarnings;
    }
    public int getPlusWaterBonusLimit(){
        return this.plusWaterBonusLimit;
    }
    public int getPlusFertBonusLimit(){
        return this.plusFertBonusLimit;
    }
    public int getSeedCostReduction(){
        return this.seedCostReduction;
    }
}
