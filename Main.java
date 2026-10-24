import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PropertyOwner owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        Tenant tenant = new Tenant(2, "Minh", "Tran", "minh@example.com");

        Property property = owner.createProperty(101, "Apartment A101, two bedrooms", 5000);
        RentalContract contract = tenant.rentProperty(1001, property,
                LocalDate.of(2026, 11, 1), LocalDate.of(2027, 10, 31));

        System.out.println("owner.getFullName(): " + owner.getFullName());
        System.out.println("Number of owner properties: " + owner.getProperties().size());
        System.out.println("Number of tenant contracts: " + tenant.getRentalContracts().size());
        System.out.println("property.isAvailable(): " + property.isAvailable());
        System.out.println("contract.isActive(): " + contract.isActive());
        System.out.println("contract.getRentAmount(): " + contract.getRentAmount());

        property.updateRentalRate(5500);
        System.out.println("\nAfter updateRentalRate(5500):");
        System.out.println("property.getRentalRate(): " + property.getRentalRate());
        System.out.println("contract.getRentAmount(): " + contract.getRentAmount());

        contract.terminate();
        System.out.println("\nAfter contract.terminate():");
        System.out.println("contract.isActive(): " + contract.isActive());
        System.out.println("property.isAvailable(): " + property.isAvailable());
        System.out.println("Contract still in tenant list: " + tenant.getRentalContracts().contains(contract));
    }
}
