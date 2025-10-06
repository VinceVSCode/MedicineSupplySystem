package BasicActions.implementation;

import BasicActions.WarehouseMovementAction;
import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.MedicineRepository;
import SaveRepo.WarehouseMovementRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



public class WarehouseMovementServiceImpl implements WarehouseMovementAction {
    private final WarehouseMovementRepository movements;
    private final MedicineRepository medicines;

    public WarehouseMovementServiceImpl(WarehouseMovementRepository movements, MedicineRepository medicines) {
        this.movements = movements;
        this.medicines = medicines;
    }

    @Override
    public WarehouseMovement createMovement(String medicineCode, String typeStr, int amount, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
        if (date == null) throw new IllegalArgumentException("date required");

        MovementType type = MovementType.valueOf(typeStr); // throws IllegalArgumentException if invalid
        Medicine med = medicines.findByCode(medicineCode)
                .orElseThrow(() -> new IllegalArgumentException("unknown medicine code"));

        // stock movement types
        switch (type) {
            case RECEIVE_MEDICINE -> med.increaseStock(amount);
            case DELIVER_MEDICINE -> {
                if (med.getStock() - amount < 0) throw new IllegalStateException("insufficient stock");
                med.decreaseStock(amount);
            }
            case OTHER_MOVEMENT -> {
                // se alles periptwseis
            }
        }
        medicines.save(med); // persist updated stock

        WarehouseMovement mv = new WarehouseMovement(null, type, med, amount, date);
        return movements.save(mv);
    }

    @Override
    public List<WarehouseMovement> findMovements(Optional<String> medicineCode,
                                                 Optional<LocalDate> dateFrom,
                                                 Optional<LocalDate> dateTo) {
        return movements.findByFilter(medicineCode, dateFrom, dateTo);
    }
}