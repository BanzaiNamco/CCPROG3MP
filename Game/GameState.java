//Orrin Landon T. Uy S17 ID12111287
/**
 * This class handles the running of the game
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameState {   
    private ArrayList<Tile> tiles;
    private Farmer player;
    private ArrayList<Tool> tools;
    private ArrayList<Seed> seeds;
    private int day;
    private Market market = new Market();

    public GameState(ArrayList<Tile> tiles, ArrayList<Seed> seeds, ArrayList<Tool> tools, Farmer player) throws FileNotFoundException{
        this.tiles = new ArrayList<Tile>(tiles);
        this.seeds = new ArrayList<Seed>(seeds);
        this.tools = new ArrayList<Tool>(tools);
        this.player = player;
    
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
                    if(market.findHarvestTotal(tiles.get(0).getSeed(), player))
                        tiles.get(0).resetTile();
                    break;
                case 4:
                    if(isFarmDead()){
                        running = false;
                        System.out.println("Farm is dead...");
                    }
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
     * Calls the appropriate player method depending on the tool to be used
     * @param toolNum  index + 1 of the tool to be used
     * @param tileIndex  index of the tile where the tool will be used on
     */
    private void useTool(int toolNum, int tileIndex){
        switch(toolNum){
            case 1:
                player.plow(tools.get(toolNum-1), tiles.get(tileIndex));
                break;
            case 2:
                player.water(tools.get(toolNum-1), tiles.get(tileIndex));
                break;
            case 3:
                player.fertilize(tools.get(toolNum-1), tiles.get(tileIndex));
                break;
            case 4:
                player.pickaxe(tools.get(toolNum-1), tiles.get(tileIndex));
                break;
            case 5:
                player.shovel(tools.get(toolNum-1), tiles.get(tileIndex));
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
}
