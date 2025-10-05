package SaveRepo.save_repo;
// imports
import MedicalSupplySystem.medical_supply_system.WarehouseMovement;
import java.util.*;
import java.time.LocalDate;

public interface WarehouseMovementRepository {
    WarehouseMovement save(WarehouseMovement movement);
    List<WarehouseMovement> findAll();
    List<WarehouseMovement> findByFilter(
        Optional<String> medicineCode,
         Optional<LocalDate> startDate,
         Optional<LocalDate> endDate
         );
}
