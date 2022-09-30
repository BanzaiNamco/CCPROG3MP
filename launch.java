package MCO;


public class launch {
    public static void main(String[] args) {
        
        int day;
        Plot plot1 = new Plot();
        day = 0;

        Player me = new Player("me");
        me.expGain(220);
        me.levelUp();
        System.out.println(me.getLevel());   
    }
}
