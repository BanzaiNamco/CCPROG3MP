package farm;

/**
 * This class represents a standard farmer.
 * <p>
 * This class extends {@link Player} and implements {@link Upgradeable}
 */
public class Farmer extends Player implements Upgradeable{

    /**
     * Constructor that creates a new object.
     * <p>
     * This constructor only calls the super constructor.
     * @param name name of the player
     */
    public Farmer(String name){
        super(name);
    }
    /**
     * Constructor that copies the passed in {@link Player} object
     * @param player {@link Player} object to be copied
     */
    protected Farmer(Player player){
        super(player);
    }

    /**
     * {@inheritDoc}
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
     * Gets the player's bonus earnings per produce
     * @return the player's bonus earnings per produce
     */
    public int getBonusEarn(){
        return 0;
    }

    /**
     * Gets the amount of object coins that the cost of a seed/crop will be reduced by
     * @return the amount of object coins that the cost of a seed/crop will be reduced by
     */
    public int getSeedCostReduction(){
        return 0;
    }

    /**
     * Gets the amount that the water bonus limit will be increased by
     * @return the amount that the water bonus limit will be increased by
     */
    public int getBonusWater(){
        return 0;
    }
    /**
     * Gets the amount that the fertilizer bonus limit will be increased by
     * @return the amount that the fertilizer bonus limit will be increased by
     */
    public int getBonusFert(){
        return 0;
    }
}
