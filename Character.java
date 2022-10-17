package proto;

abstract class Character {
    private String name;
    private int objectCoins;
    private int level;
    public double exp;

    public Character(String name){
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.exp = 0;
    }
    public int getObjectCoins(){
        return this.objectCoins;
    }
    public String getName(){
        return this.name;
    }
    public int getLevel(){
        return this.level;
    }
    public double getExp(){
        return this.exp;
    }

    public void gainExp(double exp){
        this.exp += exp;
    }
    public void useObjectCoins(int objectCoins){
        this.objectCoins -= objectCoins;
    }
    public void gainObjectCoins(int objectCoins){
        this.objectCoins += objectCoins;
    }
}
