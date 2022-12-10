package farm;

import seeds.*;
import tools.*;

/**
 * Abstract class that represents a Player.
 * <p>
 * Objects of this class have a name, level, exp, and currency called object coins.
 */
public abstract class Player {
    private final String NAME;
    private int level;
    private double exp;
    private double objectCoins;

    /**
     * Constructor of the class that creates a Player object with default values.
     * @param name the name of the player.
     */
    protected Player(String name){
        this.NAME = name;
        this.objectCoins = 1000;//TODO ADJUST
        this.level = 15;
        this.exp = 1500;
    }

    /**
     * Constructor of the class that creates a copy of the passed Player object.
     * @param player the Player object to be copied.
     */
    protected Player(Player player){
        this.NAME = player.getName();
        this.objectCoins = player.getObjectCoins();
        this.level = player.getLevel();
        this.exp = player.getExp();
    }
    
    /**
     * This method updates the player level based on the earned exp.
     * <p>
     * For every 100 exp gained, the player will level up.
     * @return true if the player leveled up, false otherwise.
     */
    public boolean update(){
        int lvl = this.level;
        this.level = (int) Math.floor(exp/100);
        if(level > lvl)
            return true;
        return false;
    }
    
    /**
     * This method plants a crop into a tile.
     * <p>
     * The method will check if the player has enough object coins to
     * plant the crop. If true then {@link farm.Tile#addCrop(Crop)} is called. 
     * <p>
     * If planting was successful, the player will lose object coins.
     * 
     * @param crop the {@link seeds.Crop} object that will be planted.
     * @param tile the {@link farm.Tile} object to be planted to.
     * @return true if planting was successful, false otherwise.
     */
    public boolean plant(Crop crop, Tile tile){
        if(objectCoins >= crop.getCost()){
            if(tile.addCrop(crop)){
                useObjectCoins(crop.getCost());
                return true;
            }   
        }
        return false;
    }
    
    /**
     * This method uses a tool on a tile.
     * <p>
     * The method will check if the player can afford to use the tool first.
     * If true, then {@link tools.Tool#use(Tile)} is called. If the called method returns true
     * then the player gains exp and loses object coins based on the {@link tools.Tool} variables.
     * Otherwise, if the called method returned false, then the method will check if tool is an instance of
     * {@link tools.Shovel}.
     * 
     * @param tool {@link tools.Tool} object to be used by the player.
     * @param tile {@link Tile} object where the tool will be used on.
     * @return true if the tool was used successfully, false otherwise.
     */
    public boolean useTool(Tool tool, Tile tile){
        if(objectCoins >= tool.getUseCost()){ //can player use tool
            if(tool.use(tile)){ //use tool
                gainExp(tool.getExpOnUse()); 
                useObjectCoins(tool.getUseCost());
                return true;
            }
            else{
                if(tool instanceof Shovel){ //shovel can not fail in being used
                    useObjectCoins(tool.getUseCost()); //player gains no exp but loses coins
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method adds to the player's object coin count.
     * @param coins amount of object coins to be added.
     */
    public void addObjectCoins(double coins){
        this.objectCoins+=coins;
        this.objectCoins = Math.round(this.objectCoins*100.0) / 100.0;
    }

    /**
     * This method deducts from the player's object coin count.
     * @param coins amount of object coins to be deducted.
     */
    public void useObjectCoins(double coins){
        this.objectCoins -= coins;
        this.objectCoins = Math.round(this.objectCoins*100.0) / 100.0;
    }

    /**
     * This method adds to the player exp.
     * @param exp amount of exp to be added.
     */
    public void gainExp(double exp){
        this.exp += exp;
    }

    /**
     * Gets the player name.
     * @return player's name.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Gets the player's object coin count.
     * @return player's object coin count.
     */
    public double getObjectCoins() {
        return this.objectCoins;
    }

    /**
     * Gets the player's level.
     * @return player's level.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the player's exp.
     * @return player's exp.
     */
    public double getExp(){
        return this.exp;
    }
}
