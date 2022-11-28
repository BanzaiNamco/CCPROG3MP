package farm;

public interface Upgradeable {
    public static Player upgrade(Player player){ 
        if(player instanceof Farmer)
            if(player.getLevel() >= 5 && player.getObjectCoins() >= 200)
                return new RegisteredFarmer(player);
        else if (player instanceof RegisteredFarmer)
            if(player.getLevel() >= 10 && player.getObjectCoins() >= 300)
                return new DistinguishedFarmer(player);
        else if(player instanceof DistinguishedFarmer)
            if(player.getLevel() >= 15 && player.getObjectCoins() >= 400)
                return new LegendaryFarmer(player);
        return player; 
    }       
}
