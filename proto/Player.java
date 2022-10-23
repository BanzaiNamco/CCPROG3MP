abstract class Player{
    private int objectCoins;
    private double exp;
    private int level;
    private final String name;
    
    public Player(String name){
        this.nanme = name;
        this.objectCoins = 100;
        this.exp = 0;
        this.level = 1;
    }


    public int getObjectCoins() {
        return this.objectCoins;
    }
    public double getExp() {
        return this.exp;
    }
    public int getLevel() {
        return this.level;
    }
    public String getName() {
        return this.name;
    }

    public void gainExp(double exp){
        this.exp += exp;
    }
    public void gainObjectCoins(int objectCoins){
        this.objectCoins += objectCoins;
    }
    public void useObjectCoins(int objectCoins){
        this.objectCoins -= objectCoins;
    }
    
}