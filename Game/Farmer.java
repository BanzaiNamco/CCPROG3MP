public class Farmer extends Player {
    private String farmer_type = "Farmer";
    private int bonusEarn = 0;
    private int minusSeedCost = 0;
    private int bonusWater = 0;
    private int bonusFertilizer = 0;
    
    public Farmer(String name){
        super(name);
    }
    //FARMER NEEDS INHERITANCE FOR THE REGISTRATION. WHY? IDK
    public void plant(Crop plant, Tile tile){
        if(tile.plant(plant)){
            useObjectCoins(plant.getCost());
        }
    }

    public void useTool(Tool tool, Tile tile){
        if(tool instanceof Useable){
            if(((Useable)tool).use(tile)){
                gainExp(tool.getExpOnUse());
                useObjectCoins(tool.getUseCost());
            }
            else{
                if(tool instanceof Shovel){
                    useObjectCoins(tool.getUseCost());
                }
            }
        }
    }
}
