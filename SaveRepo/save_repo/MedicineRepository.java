package SaveRepo.save_repo;
// imports
import MedicalSupplySystem.medical_supply_system.Medicine;
import java.util.*;

public interface MedicineRepository {
    Medicine save( Medicine medicine);
    Optional<Medicine> findById( long id);
    Optional<Medicine> findByCode( String code);
    List<Medicine> findAll();
}
