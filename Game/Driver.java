
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import GUI.*;


public class Driver {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "What is your name?");
        
        FarmModel model;
        try {
            model = new FarmModel(name);
            View1 view = new View1();
            FarmController controller = new FarmController(model, view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
