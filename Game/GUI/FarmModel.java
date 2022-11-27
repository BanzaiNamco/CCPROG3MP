package GUI;
import java.util.ArrayList;

import farm.*;


public class FarmModel {
    private Player player;
    private ArrayList<Tile> plot;
    private int[] plotMap = {
        1, 0, 0, 0, 0, 0, 1, 0, 1, 0,
        0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
        1, 0, 0, 0, 1, 1, 0, 0, 1, 0,
        0, 1, 0, 1, 0, 0, 0, 1, 0, 1,
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0
    };
    /*private ArrayList<Tool> tools;
*/
    
    public FarmModel(String name, ArrayList<Tile> plot){
        player = new Farmer(name);
        this.plot = plot;
        for (int i = 0; i < 50; i++){
            plot.add(new Tile(plotMap[i]));
        }
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public Tile getPlot(int i){
        return plot.get(i);
    }
    public int getPlotSize(){
        return plot.size();
    }
    public boolean checkStatus(){
        int ctr = 0;
        for(int i = 0; i < plot.size(); i++){
            if(plot.get(i).getCrop() != null && !plot.get(i).getCrop().getDead())
                ctr++;
        }
        if (ctr > 0)
            return true;
        return false;
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
