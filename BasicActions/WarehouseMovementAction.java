// v0.1 - service/WarehouseMovementAction.java
package BasicActions;

import MedicalSupplySystem.medical_supply_system.WarehouseMovement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WarehouseMovementAction {
    WarehouseMovement createMovement(String medicineCode, String type, int amount, LocalDate date);
    List<WarehouseMovement> findMovements(Optional<String> medicineCode,
                                          Optional<LocalDate> dateFrom,
                                          Optional<LocalDate> dateTo);
}
