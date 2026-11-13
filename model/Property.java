package model;
public class Property {
 private int propertyID; private String description; private double rentalRate; private boolean isAvailable;
 public Property(int propertyID,String description,double rentalRate){
  if(propertyID<=0)throw new IllegalArgumentException("Property ID must be positive");
  validateDescription(description);validateRentalRate(rentalRate);this.propertyID=propertyID;this.description=description.trim();this.rentalRate=rentalRate;this.isAvailable=true;
 }
 public void updateDescription(String description){validateDescription(description);this.description=description.trim();}
 public void updateRentalRate(double rentalRate){validateRentalRate(rentalRate);this.rentalRate=rentalRate;}
 private static void validateDescription(String description){if(description==null||description.trim().isEmpty())throw new IllegalArgumentException("Property description cannot be blank");}
 private static void validateRentalRate(double rentalRate){if(!Double.isFinite(rentalRate)||rentalRate<=0)throw new IllegalArgumentException("Rental rate must be a finite positive number");}
 public int getPropertyID(){return propertyID;} public String getDescription(){return description;} public double getRentalRate(){return rentalRate;} public boolean isAvailable(){return isAvailable;} public void setAvailable(boolean isAvailable){this.isAvailable=isAvailable;}
}
