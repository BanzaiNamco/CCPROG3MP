package farm;

/**
 * This class represents a Distinguished Farmer.
 * <p>
 * This class introduces adds a new variable bonusWater.
 * Values of the variables in {@link farm.RegisteredFarmer} are changed.
 */
public class DistinguishedFarmer extends RegisteredFarmer{
    /**
     * This is the amount that the water limit of a crop in the harvest calculations can be increased by.
     */
    protected int bonusWater;

    /**
     * Constructor method that creates a new DistinguishedFarmer object derived from a {@link farm.RegisteredFarmer} object.
     * <p>
     * This constructor changes the variables in {@link farm.RegisteredFarmer} and further deducts the player's object coins
     * after calling super().
     * @param farmer {@link farm.RegisteredFarmer} object to be turned into this class.
     */
    protected DistinguishedFarmer(RegisteredFarmer farmer) {
        super(farmer);
        useObjectCoins(100);
        bonusEarn = 2;
        seedCostReduction = 2;
        bonusWater = 1;
    }

    /**
     * @return a {@link farm.LegendaryFarmer} object derived from this object if upgrade is possible. Null otherwise.
    */
    @Override
    public Player upgrade(){
        if(getObjectCoins() >= 400 & getLevel() >= 15){
            Player newPlayer = new LegendaryFarmer(this);
            return newPlayer;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getObjectCoinNeed(){
        return 400;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevelNeed(){
        return 15;
    }

    /** {@inheritDoc} */
    @Override
    public int getBonusWater(){
        return bonusWater;
    }
}
