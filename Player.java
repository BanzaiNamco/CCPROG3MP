package MCO;

public class Player{
    private int objectcoins = 100;
    private int seeds = 0;
    private double exp = 0;
    private int level = 0;
    private String farmer_type;
    private String name;

    private int bonusEarn;
    private int minusSeedCost;
    private int bonusWater;
    private int bonusFertilizer;

    public Player(String name){
        this.name = name;
        this.objectcoins = 100;
        this. seeds = 0;
        this.exp = 0;
        this.level = 0;
        this.farmer_type = "Farmer";
    }

    /*Getters and setters needed */
    public void expGain(double exp){
        this.exp += exp;  
    }
    
    public void levelUp(){
        this.level = (int) this.exp / 100;
        
    }
    public void plow(Plot plotx){
        this.expGain(0.5);


    }
    public void water(Plot plotx){
        if (plotx.getIsPlowed()){
            this.expGain(0.5);
            //adjust seed stuff
        }
    }
    public void useFertilizer(Plot plotx){
        if (this.objectcoins >= 10){
            this.changeObjectCoins(-10);
            this.expGain(4.0);
            //stuff for plot and seeds
        }
    }
    public void usePickaxe(Plot plotx){
        if (this.objectcoins >=50){
            this.changeObjectCoins(-50);
            this.expGain(15);
            //stuff for plot and seeds
        }
    }
    public void useShovel(Plot plotx){
        if (this.objectcoins >= 7){
            if(plotx.getIsWithered()){
                this.changeObjectCoins(-7);
                this.expGain(2.0);
                plotx.removeWither(false);
            }
        }
    }

  /*   public class FarmerType{
        private String type;
        private int produceBonus;
        private int seedCostReduce;
        private int waterBonus;
        private int fertBonus;

        
    }*/



    public int getLevel(){
        return this.level;
    }
    public int getObjectCoins(){
        return this.objectcoins;
    }
    public int getSeeds(){
        return this.seeds;
    }
    public String getFarmerType(){
        return farmer_type;
    }
    public String getName(){
        return name;
    }
    public void changeObjectCoins(int a){
        this.objectcoins += a;
    }

}