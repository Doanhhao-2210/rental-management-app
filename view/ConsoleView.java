package view;
import controller.RentalController;import java.time.LocalDate;import java.time.format.DateTimeParseException;import java.util.NoSuchElementException;import java.util.Scanner;import model.Property;import model.PropertyOwner;import model.RentalContract;import model.Tenant;
/** Console input/output layer. It never directly creates contracts or changes model state. */
public class ConsoleView {
 private final RentalController controller;private final PropertyOwner owner;private final Tenant tenant;private final Scanner input;
 public ConsoleView(RentalController controller,PropertyOwner owner,Tenant tenant){this.controller=controller;this.owner=owner;this.tenant=tenant;this.input=new Scanner(System.in);}
 public void run(){
  while(true){System.out.println("\n=== RENTAL MANAGEMENT ===");System.out.println("1. Create property");System.out.println("2. Rent property");System.out.println("3. Terminate contract");System.out.println("4. Exit");
   try{String choice=read("Choose an option: ");switch(choice){case "1":createProperty();break;case "2":rentProperty();break;case "3":terminateContract();break;case "4":System.out.println("Goodbye!");return;default:System.out.println("Invalid menu choice. Please choose 1-4.");}}
   catch(NoSuchElementException e){System.out.println("Input ended. Goodbye!");return;}
   catch(IllegalArgumentException|IllegalStateException e){System.out.println("Error: "+e.getMessage());}
  }
 }
 private void createProperty(){int id=readInt("Property ID: ");String description=read("Description: ");double rate=readDouble("Rental rate: ");Property p=controller.createProperty(owner,id,description,rate);System.out.println("Property created: #"+p.getPropertyID()+" | Available: "+p.isAvailable());}
 private void rentProperty(){
  if(owner.getProperties().isEmpty()){System.out.println("No properties yet. Create a property first.");return;}
  System.out.println("Properties:");for(Property p:owner.getProperties())System.out.println("#"+p.getPropertyID()+" - "+p.getDescription()+" | Rate: "+p.getRentalRate()+" | Available: "+p.isAvailable());
  int id=readInt("Property ID to rent: ");Property p=findProperty(id);if(p==null)throw new IllegalArgumentException("Property ID "+id+" was not found");
  int contractId=readInt("Contract ID: ");LocalDate start=readDate("Start date (yyyy-MM-dd): ");LocalDate end=readDate("End date (yyyy-MM-dd): ");
  RentalContract c=controller.rentProperty(tenant,contractId,p,start,end);System.out.println("Contract created: #"+c.getContractID()+" | Available: "+p.isAvailable());
 }
 private void terminateContract(){if(tenant.getRentalContracts().isEmpty()){System.out.println("No rental contracts yet.");return;}int id=readInt("Contract ID to terminate: ");RentalContract c=findContract(id);if(c==null)throw new IllegalArgumentException("Contract ID "+id+" was not found");if(!c.isActive()){System.out.println("Contract is already terminated.");return;}controller.terminateContract(c);System.out.println("Contract terminated: #"+c.getContractID()+" | Available: "+c.getProperty().isAvailable());}
 private Property findProperty(int id){for(Property p:owner.getProperties())if(p.getPropertyID()==id)return p;return null;}
 private RentalContract findContract(int id){for(RentalContract c:tenant.getRentalContracts())if(c.getContractID()==id)return c;return null;}
 private String read(String prompt){System.out.print(prompt);return input.nextLine().trim();}
 private int readInt(String prompt){try{return Integer.parseInt(read(prompt));}catch(NumberFormatException e){throw new IllegalArgumentException("Please enter a valid whole number");}}
 private double readDouble(String prompt){try{return Double.parseDouble(read(prompt));}catch(NumberFormatException e){throw new IllegalArgumentException("Please enter a valid rental rate");}}
 private LocalDate readDate(String prompt){try{return LocalDate.parse(read(prompt));}catch(DateTimeParseException e){throw new IllegalArgumentException("Please enter a valid date in yyyy-MM-dd format");}}
}
