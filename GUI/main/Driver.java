public class Driver {
    public static void main(String[] args) {
        FarmModel model = new FarmModel("Name");
        View1 view = new View1();
        FarmController controller = new FarmController(model, view);
    }
}
