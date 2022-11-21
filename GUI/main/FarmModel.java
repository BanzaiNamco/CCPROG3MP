import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FarmModel {
    /*private Farmer player;
    private ArrayList<Tile> plot;
    private ArrayList<Tool> tools;
*/
    public FarmModel(String name){
    /*    player = new Farmer(name);
        plot = new ArrayList<Tile>(); //create a map, max # of rocks, etc
        tools = new ArrayList<Tool>();

        try {
            toolsInit(tools);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    } 
    private static void toolsInit(ArrayList<Tool> tools) throws FileNotFoundException{
        Scanner txtFileReader = new Scanner(new File("items/tools.txt")).useLocale(Locale.ENGLISH);
        txtFileReader.useDelimiter("-|\n");
        while(txtFileReader.hasNext()){
            String toolName = txtFileReader.next();
            int useCost = txtFileReader.nextInt();
            double exp = Double.valueOf(txtFileReader.next());
            
            tools.add(new Tool(toolName, useCost, exp));
        }
        txtFileReader.close();*/
    }
}
