import java.time.LocalDate;

public class ValidationTests {
    private static int passed = 0;

    public static void main(String[] args) {
        testUser();
        testProperty();
        testOwnerAndTenant();
        testRentalContract();
        System.out.println("All " + passed + " validation checks passed.");
    }

    private static void testUser() {
        User user = new User(2, "  Anne-Marie  ", " O'Nguyen ", " anna@example.com ");
        check(user.getUserID() == 2, "Valid user ID");
        check(user.getFullName().equals("Anne-Marie O'Nguyen"), "Valid names are trimmed");
        check(user.getEmail().equals("anna@example.com"), "Valid email is trimmed");

        shouldFail(() -> new User(0, "Anna", "Nguyen", "anna@example.com"),
                IllegalArgumentException.class, "Zero user ID");
        shouldFail(() -> new User(-1, "Anna", "Nguyen", "anna@example.com"),
                IllegalArgumentException.class, "Negative user ID");
        shouldFail(() -> new User(1, " ", "Nguyen", "anna@example.com"),
                IllegalArgumentException.class, "Blank first name");
        shouldFail(() -> new User(1, "Anna", null, "anna@example.com"),
                IllegalArgumentException.class, "Null last name");
        shouldFail(() -> new User(1, "Anna123", "Nguyen", "anna@example.com"),
                IllegalArgumentException.class, "Invalid characters in name");
        shouldFail(() -> new User(1, "Anna", "Nguyen", "anna@@example.com"),
                IllegalArgumentException.class, "Malformed email");

        shouldFail(() -> user.updateProfile("Jane", "Doe", "bad-email"),
                IllegalArgumentException.class, "Invalid profile update");
        check(user.getFullName().equals("Anne-Marie O'Nguyen"), "Invalid update preserves names");
        check(user.getEmail().equals("anna@example.com"), "Invalid update preserves email");
        user.updateProfile(" Minh ", " Tran ", "minh@example.com");
        check(user.getFullName().equals("Minh Tran"), "Valid profile update");
        check(user.getEmail().equals("minh@example.com"), "Valid email update");
    }

    private static void testProperty() {
        Property property = new Property(101, "  Apartment A101  ", 5000.50);
        check(property.getPropertyID() == 101, "Valid property ID");
        check(property.getDescription().equals("Apartment A101"), "Description is trimmed");
        check(property.getRentalRate() == 5000.50, "Positive decimal rental rate");
        check(property.isAvailable(), "New property is available");

        shouldFail(() -> new Property(0, "Apartment", 5000),
                IllegalArgumentException.class, "Zero property ID");
        shouldFail(() -> new Property(101, "  ", 5000),
                IllegalArgumentException.class, "Blank description");
        shouldFail(() -> new Property(101, null, 5000),
                IllegalArgumentException.class, "Null description");
        shouldFail(() -> new Property(101, "Apartment", 0),
                IllegalArgumentException.class, "Zero rate");
        shouldFail(() -> new Property(101, "Apartment", -50),
                IllegalArgumentException.class, "Negative rate");
        shouldFail(() -> new Property(101, "Apartment", Double.NaN),
                IllegalArgumentException.class, "NaN rate");
        shouldFail(() -> new Property(101, "Apartment", Double.POSITIVE_INFINITY),
                IllegalArgumentException.class, "Infinite rate");

        shouldFail(() -> property.updateRentalRate(Double.NEGATIVE_INFINITY),
                IllegalArgumentException.class, "Invalid rate update");
        check(property.getRentalRate() == 5000.50, "Invalid rate update preserves old rate");
        shouldFail(() -> property.updateDescription(" "),
                IllegalArgumentException.class, "Invalid description update");
        check(property.getDescription().equals("Apartment A101"),
                "Invalid description update preserves old description");
        property.updateRentalRate(5500);
        check(property.getRentalRate() == 5500, "Valid rate update");
    }

    private static void testOwnerAndTenant() {
        shouldFail(() -> new PropertyOwner(-2, "Anna", "Nguyen", "anna@example.com"),
                IllegalArgumentException.class, "Owner inherits user validation");
        shouldFail(() -> new Tenant(2, " ", "Tran", "minh@example.com"),
                IllegalArgumentException.class, "Tenant inherits user validation");

        PropertyOwner owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        shouldFail(() -> owner.createProperty(101, "Apartment", Double.NaN),
                IllegalArgumentException.class, "Owner cannot create invalid property");
        check(owner.getProperties().isEmpty(), "Failed property is not appended");
        Property property = owner.createProperty(101, "Apartment", 5000);
        check(owner.getProperties().size() == 1, "Valid property is appended");

        Tenant tenant = new Tenant(2, "Minh", "Tran", "minh@example.com");
        LocalDate start = LocalDate.of(2026, 11, 1);
        LocalDate end = LocalDate.of(2027, 10, 31);
        shouldFail(() -> tenant.rentProperty(1001, property, end, start),
                IllegalArgumentException.class, "Tenant cannot create contract with reversed dates");
        check(tenant.getRentalContracts().isEmpty(), "Failed contract is not appended");
        check(property.isAvailable(), "Failed rental does not change property availability");

        RentalContract contract = tenant.rentProperty(1001, property, start, end);
        check(tenant.getRentalContracts().size() == 1, "Valid contract is appended");
        shouldFail(() -> tenant.rentProperty(1002, property, start, end),
                IllegalStateException.class, "Already rented property cannot be rented again");
        check(tenant.getRentalContracts().size() == 1, "Failed second rental is not appended");
        contract.terminate();
        check(property.isAvailable(), "Terminating rental restores availability");
    }

    private static void testRentalContract() {
        Property property = new Property(200, "House", 1000);
        Tenant tenant = new Tenant(3, "Huy", "Le", "huy@example.com");
        LocalDate start = LocalDate.of(2026, 11, 1);
        LocalDate end = LocalDate.of(2027, 10, 31);

        shouldFail(() -> new RentalContract(-4, property, tenant, start, end),
                IllegalArgumentException.class, "Negative contract ID");
        shouldFail(() -> new RentalContract(1, null, tenant, start, end),
                IllegalArgumentException.class, "Null property");
        shouldFail(() -> new RentalContract(1, property, null, start, end),
                IllegalArgumentException.class, "Null tenant");
        shouldFail(() -> new RentalContract(1, property, tenant, null, end),
                IllegalArgumentException.class, "Null start date");
        shouldFail(() -> new RentalContract(1, property, tenant, start, null),
                IllegalArgumentException.class, "Null end date");
        shouldFail(() -> new RentalContract(1, property, tenant, start, start),
                IllegalArgumentException.class, "Equal dates");
        shouldFail(() -> new RentalContract(1, property, tenant, end, start),
                IllegalArgumentException.class, "Reversed dates");
        check(property.isAvailable(), "Invalid contract does not change availability");

        RentalContract contract = new RentalContract(1, property, tenant, start, end);
        check(contract.isActive(), "Valid contract is active");
        check(!property.isAvailable(), "Active contract reserves property");
        check(contract.getRentAmount() == 1000, "Contract records initial rental rate");
        property.updateRentalRate(1200);
        check(contract.getRentAmount() == 1000, "Contract amount does not change with property rate");
        shouldFail(() -> new RentalContract(2, property, tenant, start, end),
                IllegalStateException.class, "Unavailable property");
        check(!property.isAvailable(), "Rejected contract leaves property rented");

        contract.terminate();
        check(!contract.isActive(), "Terminated contract becomes inactive");
        check(property.isAvailable(), "Terminated contract releases property");
        contract.terminate();
        check(property.isAvailable(), "Repeated termination is safe");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
        passed++;
    }

    private static void shouldFail(Runnable action,
                                   Class<? extends RuntimeException> expected, String message) {
        try {
            action.run();
        } catch (RuntimeException e) {
            check(expected.isInstance(e), message + ": wrong exception " + e);
            return;
        }
        throw new AssertionError(message + ": expected an exception");
    }
}
