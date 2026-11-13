package controller;
import java.time.LocalDate;import model.Property;import model.PropertyOwner;import model.RentalContract;import model.Tenant;
/** Coordinates requests from the View and delegates business rules to Model classes. */
public class RentalController {
 public Property createProperty(PropertyOwner owner,int id,String description,double rate){return owner.createProperty(id,description,rate);}
 public RentalContract rentProperty(Tenant tenant,int contractId,Property property,LocalDate startDate,LocalDate endDate){return tenant.rentProperty(contractId,property,startDate,endDate);}
 public void terminateContract(RentalContract contract){contract.terminate();}
}
