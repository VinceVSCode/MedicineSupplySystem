// stoxos einai na apothikeuoume ta MedicineCategory objects
package SaveRepo;

// imports
import MedicalSupplySystem.medical_supply_system.MedicineCategory;
import java.util.*;

public interface MedicineCategoryRepository {
    MedicineCategory save( MedicineCategory category);
    Optional<MedicineCategory> findById( Long id);
    Optional<MedicineCategory> findByName ( String name);
    List<MedicineCategory> findAll();
    
}
