import java.awt.*;
import java.awt.image.BufferedImage;

public class FarmController {
    private FarmModel model;
    private View1 view;
    private Graphics2D g;
    public FarmController(FarmModel model, View1 view){
        this.view = view;
        this.model = model;

        view.setPlayerNameTxt("orrin");
        view.setCoinsTxt("100");
        view.setDayTxt("1");
        view.setLevelTxt("0");
        view.setExpTxt("0");
    }

    
}
