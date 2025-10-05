// v0.1 - service/MedicineAction.java
package BasicActions;

import MedicalSupplySystem.medical_supply_system.Medicine;
import java.math.BigDecimal;
import java.util.List;

public interface MedicineAction {
    Medicine createMedicine(String code, String name, BigDecimal price, int initialStock, String categoryName);
    List<Medicine> listAllMedicines();
}
