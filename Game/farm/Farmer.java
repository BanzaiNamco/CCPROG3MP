package farm;

public class Farmer extends Player implements Upgradeable{

    public Farmer(String name){
        super(name);
    }

    @Override
    public Player upgrade2() {
        if(getObjectCoins() >= 200 && getLevel() >= 5)
            return new RegisteredFarmer(this);
        return null;
    }

    @Override
    public double getObjectCoinNeed() {
        return 200 - getObjectCoins();
    }

    @Override
    public int getLevelNeed() {
        return 5 - getLevel();
    }
}
