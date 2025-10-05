// v0.1 - service/impl/ServicesImpl.java
package BasicActions.implementation;

import BasicActions.MedicineAction;
import BasicActions.WarehouseMovementAction;
import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MedicineServiceImpl implements MedicineAction {
    private final MedicineRepository medicines;
    private final MedicineCategoryRepository categories;

    public MedicineServiceImpl(MedicineRepository medicines, MedicineCategoryRepository categories) {
        this.medicines = medicines;
        this.categories = categories;
    }

    @Override
    public Medicine createMedicine(String code, String name, BigDecimal price, int initialStock, String categoryName) {
        if (code == null || code.isBlank()) throw new IllegalArgumentException("code required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (price == null || price.signum() < 0) throw new IllegalArgumentException("price must be >= 0");
        if (initialStock < 0) throw new IllegalArgumentException("stock must be >= 0");

        medicines.findByCode(code).ifPresent(m -> { throw new IllegalArgumentException("code already exists"); });

        MedicineCategory category = categories.findByName(categoryName)
                .orElseGet(() -> categories.save(new MedicineCategory(null, categoryName)));

        Medicine med = new Medicine(null, code, name, price, initialStock, category);
        return medicines.save(med);
    }

    @Override
    public List<Medicine> listAllMedicines() {
        return medicines.findAll();
    }
}


