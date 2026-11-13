package model;
import java.time.LocalDate;import java.util.ArrayList;import java.util.List;
public class Tenant extends User {
 private List<RentalContract> rentalContracts;
 public Tenant(int userID,String firstName,String lastName,String email){super(userID,firstName,lastName,email);rentalContracts=new ArrayList<>();}
 public RentalContract rentProperty(int contractID,Property property,LocalDate startDate,LocalDate endDate){RentalContract contract=new RentalContract(contractID,property,this,startDate,endDate);rentalContracts.add(contract);return contract;}
 public List<RentalContract> getRentalContracts(){return rentalContracts;}
}
