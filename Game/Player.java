public abstract class Player {
    private final String name;
    private double objectCoins;
    private int level;
    private double exp;
    
    public Player(String name){
        this.name = name;
        this.objectCoins = 100;
        this.level = 0;
        this.exp = 0;
    }
     /**
     * This method updates the player level depending on the amount of exp the player has
     */
    public void update(){
        if (this.level <= Math.floor(exp/100) && this.exp > (this.level * 100) + 99){ //probably a better way to do this tbh
            this.level++;
        }
    }
    protected void addObjectCoins(double coins){
        objectCoins += coins;
    }

    protected void useObjectCoins(double coins){
        objectCoins -= coins;
    }

    protected void gainExp(double exp){
        exp += exp;
    }

    public String getName() {
        return this.name;
    }

    public double getObjectCoins() {
        return this.objectCoins;
    }

    public int getLevel() {
        return this.level;
    }

    public double getExp(){
        return this.exp;
    }
}
