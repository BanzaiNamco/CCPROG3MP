//Orrin Landon T. Uy S17 ID12111287\
/**
 * This class handles all basic functions of a non-specialized player
 */
public class Player{
    private int objectCoins;
    private double exp;
    private int level;
    private final String name;

    
    public Player(String name){
        this.name = name;
        this.objectCoins = 100;
        this.exp = 0;
        this.level = 0;
    }
    /**
     * This method updates the player level depending on the amount of exp the player has
     */
    public void update(){
        if (this.level <= Math.floor(exp/100) && this.exp > (this.level * 100) + 99){ //probably a better way to do this tbh
            this.level++;
        }
    }

    /**
     * This method adds to the current exp of the player
     * @param exp  amount of exp to be added
     */
    protected void gainExp(double exp){
        this.exp += exp;
    }
    /**
     * This method adds objectCoins to the player's objectCoin count
     * @param objectCoins  amount of objectCoins to be added
     */
    protected void gainObjectCoins(int objectCoins){
        this.objectCoins += objectCoins;
    }
    /**
     * This method deducts the player's objectCoins
     * @param objectCoins  amount of objectCoins to be deducted
     */
    protected void useObjectCoins(int objectCoins){
        this.objectCoins -= objectCoins;
    }
    /**
     * Returns current objectCoin count
     * @return  objectCoins
     */
    public int getObjectCoins() {
        return this.objectCoins;
    }
    /**
     * Returns current exp
     * @return  exp
     */
    public double getExp() {
        return this.exp;
    }
    /**
     * Returns current level
     * @return level
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * Returns player name
     * @return name
     */
    public String getName() {
        return this.name;
    }
    

    
    
}