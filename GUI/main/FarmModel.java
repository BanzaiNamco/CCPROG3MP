import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*import farm.*;
import seeds.*;
import tools.*;*/

public class FarmModel {
  /*   private Player player;
    private ArrayList<Tile> plot;
    private ArrayList<Tool> tools;
*/
    private String name;
    public FarmModel(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    /*public FarmModel(Player player, ArrayList<Tool> tools, ArrayList<Tile> plot){
        this.player = player;
        this.plot = new ArrayList<Tile>(plot); //create a map, max # of rocks, etc
        this.tools = new ArrayList<Tool>(tools);

        
    }*/ 
    /*private static void toolsInit(ArrayList<Tool> tools) throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("items/tools.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
        while(txtFileReader.hasNext()){
            String toolName = txtFileReader.next();
            int useCost = txtFileReader.nextInt();
            double exp = Double.valueOf(txtFileReader.next());
            
            tools.add(new Tool(toolName, useCost, exp));
        }
        txtFileReader.close();
    }*/
}
