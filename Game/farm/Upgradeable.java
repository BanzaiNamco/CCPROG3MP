//Orrin Landon T. Uy ID12111287
package farm;

/**
 * This interface provides the contracts for creating an upgradeable class.
 */
public interface Upgradeable {
    /**
     * This method checks if the player is eligible to upgrade into
     * the next farmer type and upgrades if the requirements are met.
     * <p>
     * If the player is eligible, a new instance of
     * the upgraded player is made.
     * @return a new instance of the current object but as its subclass.
     */
    public Player upgrade();
    /**
     * Gets the amount of object coins the player needs to upgrade to the next
     * farmer type.
     * @return object coins needed to upgrade
     */
    public int getObjectCoinNeed();
    /**
     * Gets the minimum level requirement to upgrade farmer type.
     * @return minimum level requirement to upgrade
     */
    public int getLevelNeed();
}
