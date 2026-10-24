public class Property {
    private int propertyID;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    public Property(int propertyID, String description, double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero");
        }
        this.propertyID = propertyID;
        this.description = description;
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    // If the new rate is invalid, throw an error and keep the old rate
    public void updateRentalRate(double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero");
        }
        this.rentalRate = rentalRate;
    }

    public int getPropertyID() { return propertyID; }
    public String getDescription() { return description; }
    public double getRentalRate() { return rentalRate; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
}
