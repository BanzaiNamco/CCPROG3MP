package farm;

/**
 * This class is the highest level in the Farmer Series.
 * <p>
 * This class extends {@link farm.DistinguishedFarmer}.
 * The bonusFert variable is introduced here.
 */
public class LegendaryFarmer extends DistinguishedFarmer{
    protected int bonusFert;

    /**
     * This constructor creates a LegendaryFarmer object derived from a {@link farm.DistinguishedFarmer} object.
     * <p>
     * This constructor deducts the player's object coins after calling super().
     * All the values of variables starting from {@link farm.RegisteredFarmer} are changed here.
     * @param player
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
     * Gets the instance of this object
     * @return this instance
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
}
