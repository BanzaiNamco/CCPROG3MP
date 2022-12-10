//Orrin Landon T. Uy ID12111287
package farm;

/**
 * This class is the highest level in the Farmer Series.
 * <p>
 * The bonusFert variable is introduced here.
 */
public class LegendaryFarmer extends DistinguishedFarmer{
    /**
     * This is the amount that the water limit of a crop in the harvest calculations can be increased by.
     */
    protected int bonusFert;

    /**
     * This constructor creates a LegendaryFarmer object derived from a {@link farm.DistinguishedFarmer} object.
     * <p>
     * This constructor deducts the player's object coins after calling super().
     * All the values of protected variables starting from {@link farm.RegisteredFarmer} are changed here.
     * @param player {@link farm.DistinguishedFarmer} object to be turned into this class.
     */
    protected LegendaryFarmer(DistinguishedFarmer player) {
        super(player);
        useObjectCoins(100);
        bonusEarn = 4;
        seedCostReduction = 3;
        bonusWater = 2;
        bonusFert = 1;
    }

    /**
     * Gets the instance of this object.
     * @return this instance.
     */
    @Override
    public Player upgrade(){
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public int getBonusFert(){
        return bonusFert;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getObjectCoinNeed(){
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevelNeed(){
        return 0;
    }
}
