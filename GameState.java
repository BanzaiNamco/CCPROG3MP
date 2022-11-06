import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameState {   
    private ArrayList<Tile> tiles;
    private Farmer player;
    private ArrayList<Tool> tools;
    private ArrayList<Seed> seeds;
    private int day;

    public GameState(String input) throws FileNotFoundException{
        tiles = new ArrayList<Tile>();
        seeds = new ArrayList<Seed>();
        tools = new ArrayList<Tool>();
        player = new Farmer(input);

        tiles.add(new Tile());
        seedsInit(seeds);
        toolsInit(tools);
    }
    /**
     * Starts the game and keeps it running
     */
    public void run(){
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while(running){
            printDisplay();
            //then get index of tile to change
            switch(input.nextInt()){
                case 1:
                    System.out.println("\nChoose tool to use:");
                    System.out.println("1. Plow\n2. Watering Can\n3. Fertilizer\n4. Pickaxe\n5. Shovel\n6. Cancel");
                    useTool(input.nextInt(), 0);
                    break;
                case 2:
                    player.plantSeed(seeds.get(0), tiles.get(0));
                    break;
                case 3:
                    if(player.harvest(tiles.get(0), findHarvestTotal(0))){
                        System.out.println("Harvest Successful!");
                    }
                    else
                        System.out.println("Harvest Failed!");
                    break;
                case 4:
                    if(isFarmDead())
                        running = false;
                    day++;
                    break;
                case 5:
                    tiles.get(0).showStats();
                    break;
                case 6:
                    running = false;
                    break;
                case 7:
                    player.addEXP(50);
                    break;
                default: System.out.println("error");
            }
            player.update();
        }
        input.close();
    }
    /**
     * This method calculates the Total Harvest Price of a plant that is ready for harvesting
     * <p>
     * This method returns the total harvest price for the passed Seed object
     * whether or not the Seed object is ready for harvesting or not
     * 
     * @param seed  Seed object whose total harvest price is being calculated for
     * @return harvestTotal, the total harvesting price of seed
     */
    private int findHarvestTotal(int index){
        if(tiles.get(index).getSeed() != null){
            Seed seed = tiles.get(index).getSeed();
            int harvestTotal = findRandomProduce(seed.getProduceMin(), seed.getProduceMax());
            if(seed.getHarvestable())
                System.out.println(seed.getName() + " produced " + harvestTotal + " products");
            harvestTotal *= (seed.getBaseSellingPrice() + player.getBonusEarn());
            harvestTotal +=  findWaterBonus(harvestTotal, seed.getTimesWatered()) + findFertilizerBonus(harvestTotal, seed.getTimesFertilized());

            if(seed.getCropType().equals("Flower"))
                harvestTotal *= 1.1;

            return harvestTotal;
        }
        return 0;
    }
    /**
     * This method returns a random number given a minimum and a maximum
     * 
     * @param min  minimum bound for the random number
     * @param max  maximum bound for the random number
     * @return  random number [min, max]
     */
    private int findRandomProduce(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    /**
     * Returns the bonus objectCoins to be earned from adding water
     * @param harvestTotal  amount to be earned without bonuses
     * @param timesWatered  times the plant was watered
     * @return
     */
    private double findWaterBonus(int harvestTotal, int timesWatered){
        return harvestTotal * 0.2 * (timesWatered-1);
    }
    /**
     * Returns the bonus objectCoins to be earned from adding fertilizer
     * @param harvestTotal  amount to be earned without bonuses
     * @param timesFertilized  times the plant was fertilized
     * @return
     */
    private double findFertilizerBonus(int harvestTotal, int timesFertilized){
        return harvestTotal * 0.5 * timesFertilized;
    }
    /**
     * Checks if any plant is still alive on the field
     * @return true if there is no more surviving plants
     *         false otherwise
     */
    private boolean isFarmDead(){
        int ctr = 0;
        for (int i = 0; i < tiles.size(); i++){
            if(!tiles.get(i).getHasWithered() && tiles.get(i).update())
                ctr++;
        }
        if(ctr == 0)
            return true;
        else
            return false;
    }
    /**
     * 
     * @param input
     * @param tileIndex
     */
    private void useTool(int input, int tileIndex){
        switch(input){
            case 1:
                player.useTool(tools.get(0), tiles.get(tileIndex));
                break;
            case 2:
                player.useTool(tools.get(1), tiles.get(tileIndex));
                break;
            case 3:
                player.useTool(tools.get(2), tiles.get(tileIndex));
                break;
            case 4:
                player.useTool(tools.get(3), tiles.get(tileIndex));
                break;
            case 5:
                player.useTool(tools.get(4), tiles.get(tileIndex));
                break;
                    }
    }
    /**
     * Displays action options and player related stats. Also displays day number
     */
    private void printDisplay(){
        System.out.println();
        System.out.println(player.getName() + " Day " + day);
        System.out.println("Level " + player.getLevel() + " EXP: " + player.getExp());
        System.out.println("ObjectCoins: " + player.getObjectCoins());
        System.out.println("What to do?");
        System.out.println("1. Use Tool\n2. Plant Turnip\n3. Harvest\n4. Advance Day\n5. Show tile stats\n6. End Game");
    }
    /**
     * Initializes the seed ArrayList using file input
     * @param seeds  ArrayList of Seed objects
     * @throws FileNotFoundException
     */
    private void seedsInit(ArrayList<Seed> seeds) throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("items/seeds.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
        while(txtFileReader.hasNext()){
            String cropName = txtFileReader.next();
            String cropType = txtFileReader.next();
            int harvestTime = txtFileReader.nextInt();
            int waterNeeds = txtFileReader.nextInt();
            int fertNeeds = txtFileReader.nextInt();
            int prodMin = txtFileReader.nextInt();
            int prodMax = txtFileReader.nextInt();
            int cost = txtFileReader.nextInt();
            int bsp = txtFileReader.nextInt();
            double exp = Double.valueOf(txtFileReader.next());

            seeds.add(new Seed(cropName, cropType, harvestTime, waterNeeds, fertNeeds, prodMin, prodMax, cost, bsp, exp));
        }   
        txtFileReader.close(); 
    }
    /**
     * Initializes the tools ArrayList using file input
     * @param tools  ArrayList of Tool objects
     * @throws FileNotFoundException
     */
    private void toolsInit(ArrayList<Tool> tools) throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("items/tools.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
        while(txtFileReader.hasNext()){
            String toolName = txtFileReader.next();
            int useCost = txtFileReader.nextInt();
            double exp = Double.valueOf(txtFileReader.next());
            
            tools.add(new Tool(toolName, useCost, exp));
        }
        txtFileReader.close();
    }
}
