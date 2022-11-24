import javax.swing.JOptionPane;


public class Driver {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "What is your name?");
        FarmModel model = new FarmModel(name);
        View1 view = new View1();
        FarmController controller = new FarmController(model, view);
    }
}
