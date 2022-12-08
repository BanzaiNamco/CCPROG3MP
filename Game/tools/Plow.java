package tools;

import farm.*;

/**
 * This class represents the Plow tool and extends from {@link tools.Tool}
 */
public class Plow extends Tool{
    /**
     * Constructor for this class
     * @param name name of the tool
     * @param useCost cost of using the tool
     * @param exp exp earned from using the tool
     */
    public Plow(String name, double useCost, double exp){
        super(name, useCost, exp);
    }

    /**
     * This method calls {@link farm.Tile#changePlowStatus()}.
     * @param tile tile to be plowed
     * @return {@link farm.Tile#changePlowStatus()}
     */
    @Override
    public boolean use(Tile tile) {
        return tile.changePlowStatus();
    }
}
