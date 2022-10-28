import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Driver {
    private static void addTools(ArrayList<Tool> tools) throws FileNotFoundException{
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
    private static void addSeeds(ArrayList<Seed> seeds) throws FileNotFoundException{
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
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Tool> tools = new ArrayList<Tool>();
        ArrayList<Seed> seeds = new ArrayList<Seed>();
        ArrayList<Tile> plot = new ArrayList<Tile>();
        // for (int i = 0; i < 50; i ++){
            plot.add(new Tile());
       // } 

        boolean gameState = true;
        int day = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Farming Game #12111287");
        System.out.println("Please enter your name: ");
        Farmer player = new Farmer(input.next());
        
        addTools(tools);
        addSeeds(seeds);
        
        while(gameState){
            System.out.println();
            System.out.println(player.getName() + " Day " + day);
            System.out.println("Level " + player.getLevel() + " EXP: " + player.getExp());
            System.out.println("ObjectCoins: " + player.getObjectCoins());
            System.out.println("What to do?");
            System.out.println("1. Use Tool\n2. Plant Turnip\n3. Harvest\n4. Advance Day\n5. Show tile stats\n6. End Game");
            switch(input.nextInt()){
                case 1:
                    System.out.println("\nChoose tool to use:");
                    System.out.println("1. Plow\n2. Watering Can\n3. Fertilizer\n4. Pickaxe\n5. Shovel\n6. Cancel");
                    switch(input.nextInt()){
                        case 1:
                            player.useTool(tools.get(0), plot.get(0));
                            break;
                        case 2:
                            player.useTool(tools.get(1), plot.get(0));
                            break;
                        case 3:
                            player.useTool(tools.get(2), plot.get(0));
                            break;
                        case 4:
                            player.useTool(tools.get(3), plot.get(0));
                            break;
                        case 5:
                            player.useTool(tools.get(4), plot.get(0));
                            break;
                    }
                    break;
                case 2:
                    player.plantSeed(seeds.get(0), plot.get(0));
                    break;
                case 3:
                    if(player.harvest(plot.get(0))){
                        System.out.println("Harvest Successful!");
                    }
                    else
                        System.out.println("Harvest Failed!");
                    break;
                case 4:
                    int ctr = 0;
                    
                    for (int i = 0; i < plot.size(); i++){
                        if(!plot.get(i).getHasWithered() && plot.get(i).update())
                            ctr++;
                    }
                    if(ctr == 0)
                        gameState = false;
                    day++;
                    break;
                case 5:
                    plot.get(0).showStats();
                    break;
                case 6:
                    gameState = false;
                    break;
                case 7:
                    player.addEXP(50);
                    break;
                default: System.out.println("error");
            }
            player.update();
        }
        System.out.println("Game over");
        input.close();
    }
}
