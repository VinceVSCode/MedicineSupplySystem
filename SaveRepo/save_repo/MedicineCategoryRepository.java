// stoxos einai na apothikeuoume ta MedicineCategory objects
package SaveRepo.save_repo;

// imports
import MedicalSupplySystem.medical_supply_system.MedicineCategory;
import java.util.*;

public interface MedicineCategoryRepository {
    MedicineCategory save( MedicineCategory category);
    Optional<MedicineCategory> findById( long id);
    Optional<MedicineCategory> findByName ( String name);
    List<MedicineCategory> findAll();
    
}
