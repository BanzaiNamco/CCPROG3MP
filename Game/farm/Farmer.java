package farm;

/**
 * This class represents a standard farmer.
 */
public class Farmer extends Player implements Upgradeable{

    /**
     * Constructor that creates a new Farmer object with the specified name.
     * <p>
     * This constructor only calls the super constructor.
     * @param name name of the player.
     */
    public Farmer(String name){
        super(name);
    }
    /**
     * Constructor that copies the passed in {@link Player} object.
     * @param player {@link Player} object to be copied.
     */
    protected Farmer(Player player){
        super(player);
    }

    /**
     * @return a {@link farm.RegisteredFarmer} object derived from this object. Null otherwise.
     */
    @Override
    public Player upgrade() {
        if(getObjectCoins() >= 200 && getLevel() >= 5){
            Player newPlayer = new RegisteredFarmer(this);
            return newPlayer;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getObjectCoinNeed(){
        return 200;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevelNeed(){
        return 5;
    }
    /**
     * Gets the player's bonus earnings per produce.
     * @return the player's bonus earnings per produce.
     */
    public int getBonusEarn(){
        return 0;
    }

    /**
     * Gets the amount of object coins that the cost of a seed/crop will be reduced by.
     * @return the amount of object coins that the cost of a seed/crop will be reduced by.
     */
    public int getSeedCostReduction(){
        return 0;
    }

    /**
     * Gets the amount that the water bonus limit will be increased by.
     * @return the amount that the water bonus limit will be increased by.
     */
    public int getBonusWater(){
        return 0;
    }
    /**
     * Gets the amount that the fertilizer bonus limit will be increased by.
     * @return the amount that the fertilizer bonus limit will be increased by.
     */
    public int getBonusFert(){
        return 0;
    }
}
