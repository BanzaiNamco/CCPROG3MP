package farm;

import seeds.*;

/**
 * This class represents a Registered Farmer.
 * <p>
 * This class introduces the bonusEarn and seedCostReduction variables.
 */
public class RegisteredFarmer extends Farmer{
    protected int bonusEarn;
    protected int seedCostReduction;

    /**
     * Constructor that creates a new RegisteredFarmer object derived from its superclass {@link farm.Farmer}.
     * <p>
     * Player object coins is deducted in this constructor.
     * @param farmer {@link farm.Farmer} object to be turned into this class.
     */
    protected RegisteredFarmer(Farmer farmer){
        super(farmer);
        useObjectCoins(200);
        bonusEarn = 1;
        seedCostReduction = 1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean plant(Crop crop, Tile tile){
        if(tile.addCrop(crop)){
            useObjectCoins(crop.getCost() - seedCostReduction);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBonusEarn(){
        return bonusEarn;
    }

    /**
     * @return a {@link farm.DistinguishedFarmer} object derived from this object if possible. Null otherwise.
     */
    @Override
    public Player upgrade() {
        if(getObjectCoins() >= 300 && getLevel() >= 10){
            Player newPlayer = new DistinguishedFarmer(this);
            return newPlayer;
        }
        return null;
    }
}
