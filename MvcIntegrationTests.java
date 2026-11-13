import controller.RentalController;
import java.time.LocalDate;
import model.Property;
import model.PropertyOwner;
import model.RentalContract;
import model.Tenant;

public class MvcIntegrationTests {
    private static int passed = 0;
    public static void main(String[] args) {
        RentalController controller = new RentalController();
        PropertyOwner owner = new PropertyOwner(10, "Test", "Owner", "owner@example.com");
        Tenant tenant = new Tenant(11, "Test", "Tenant", "tenant@example.com");

        Property property = controller.createProperty(owner, 101, "Apartment", 1200);
        check(property.isAvailable(), "New property is available");
        RentalContract contract = controller.rentProperty(tenant, 1001, property,
                LocalDate.of(2026, 11, 1), LocalDate.of(2027, 1, 1));
        check(!property.isAvailable(), "Renting makes property unavailable");
        check(contract.isActive(), "New contract is active");
        controller.terminateContract(contract);
        check(property.isAvailable(), "Termination restores availability");
        check(!contract.isActive(), "Terminated contract is inactive");

        Property invalidRateProperty = null;
        try {
            controller.createProperty(owner, 102, "Invalid", 0);
        } catch (IllegalArgumentException expected) {
            passed++;
        }
        check(owner.getProperties().size() == 1, "Invalid property is not stored");

        try {
            controller.rentProperty(tenant, 1002, property,
                    LocalDate.of(2027, 2, 1), LocalDate.of(2027, 1, 1));
            throw new AssertionError("Invalid dates should be rejected");
        } catch (IllegalArgumentException expected) {
            passed++;
        }
        check(property.isAvailable(), "Invalid contract does not change availability");
        check(tenant.getRentalContracts().size() == 1, "Invalid contract is not stored");
        System.out.println("All " + passed + " MVC integration checks passed.");
    }
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
        passed++;
    }
}
