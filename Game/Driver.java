
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI.*;
import farm.Tile;


public class Driver {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "What is your name?");
        ArrayList<Tile> plot = new ArrayList<Tile>();
        FarmModel model = new FarmModel(name, plot);
        View1 view = new View1();
        FarmController controller = new FarmController(model, view);
    }
}
