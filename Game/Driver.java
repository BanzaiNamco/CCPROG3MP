
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import GUI.*;


public class Driver {
    public static void main(String[] args) {
        String name =  JOptionPane.showInputDialog(null, "What is your name?");
        if(name != null){
            FarmModel model;
            try {
                model = new FarmModel(name);
                FarmView view = new FarmView();
                FarmController controller = new FarmController(model, view);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
