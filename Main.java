import controller.RentalController;
import model.PropertyOwner;
import model.Tenant;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        PropertyOwner owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        Tenant tenant = new Tenant(2, "Minh", "Tran", "minh@example.com");
        RentalController controller = new RentalController();
        ConsoleView view = new ConsoleView(controller, owner, tenant);
        view.run();
    }
}
