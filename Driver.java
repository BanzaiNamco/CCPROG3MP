//Orrin Landon T. Uy S17 ID12111287
//This is the Driver code

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Farming Game #12111287");
        System.out.println("Please enter your name: ");
        String name = input.next();
        
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        ArrayList<Seed> seeds = new ArrayList<Seed>();
        ArrayList<Tool> tools = new ArrayList<Tool>();
        Farmer player = new Farmer(name);

        seedsInit(seeds);
        toolsInit(tools);
        tiles.add(new Tile());
        GameState game = new GameState(tiles, seeds, tools, player);
        game.run();

        System.out.println("Game over");
        input.close();
    }

    /**
     * Initializes the seed ArrayList using file input
     * @param seeds  ArrayList of Seed objects
     * @throws FileNotFoundException
     */
    private static void seedsInit(ArrayList<Seed> seeds) throws FileNotFoundException{
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
    private static void toolsInit(ArrayList<Tool> tools) throws FileNotFoundException{
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
