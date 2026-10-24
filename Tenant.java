import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tenant extends User {
    private List<RentalContract> rentalContracts;

    public Tenant(int userID, String firstName, String lastName, String email) {
        super(userID, firstName, lastName, email);
        this.rentalContracts = new ArrayList<>();
    }

    // If the RentalContract constructor throws, nothing is added to the list
    public RentalContract rentProperty(int contractID, Property property,
                                       LocalDate startDate, LocalDate endDate) {
        RentalContract contract = new RentalContract(contractID, property, this, startDate, endDate);
        rentalContracts.add(contract);
        return contract;
    }

    // Returns all contracts, including terminated ones
    public List<RentalContract> getRentalContracts() {
        return rentalContracts;
    }
}
