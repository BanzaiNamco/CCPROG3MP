package farm;

public interface Upgradeable {
    /**
     * This method checks if the player is eligible to upgrade into
     * the next farmer type. If the player is eligible, a new instance of
     * the upgraded player is made
     * @return a new instance of the current object but as its subclass
     */
    public Player upgrade();
}
