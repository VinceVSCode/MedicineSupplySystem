// v0.1 - Demo runner for the Medicine Manager (in-memory)
package app;

import BasicActions.*;
import MedicalSupplySystem.*;
import SaveRepo.*;
import BasicActions.implementation.*;
import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.save_repo.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // --- Wire repositories (in-memory) ---
        MedicineCategoryRepository categoryRepo = new InMemoryMedicineCategoryRepository();
        MedicineRepository medicineRepo = new InMemoryMedicineRepository();
        WarehouseMovementRepository movementRepo = new InMemoryWarehouseMovementRepository();

        // --- Wire services ---
        MedicineAction medicineService = new MedicineServiceImpl(medicineRepo, categoryRepo);
        WarehouseMovementAction movementService = new WarehouseMovementServiceImpl(movementRepo, medicineRepo);

        // --- Create a few medicines ---
        Medicine paracetamol = medicineService.createMedicine(
                "MED-001", "Paracetamol 500mg", new BigDecimal("2.49"), 50, "Painkiller");
        Medicine vitaminC = medicineService.createMedicine(
                "MED-002", "Vitamin C 1000mg", new BigDecimal("4.99"), 20, "Supplement");

        // --- Show all medicines (initial state) ---
        System.out.println("== Medicines (initial) ==");
        printMedicines(medicineService.listAllMedicines());

        // --- Create movements (now using safe helper) ---
        // v0.1.1 CHANGED: use safeAttempt(...) so errors are thrown by service but handled here.

        // 1) Deliver 30 Paracetamol on 2025-10-01
        safeAttempt(() ->
            movementService.createMovement("MED-001", MovementType.DELIVER_MEDICINE.name(), 30, LocalDate.of(2025, 10, 1)),
            "Deliver 30 Paracetamol on 2025-10-01");

        // 2) Order 40 Paracetamol on 2025-10-02
        safeAttempt(() ->
            movementService.createMovement("MED-001", MovementType.RECEIVE_MEDICINE.name(), 40, LocalDate.of(2025, 10, 2)),
            "Order 40 Paracetamol on 2025-10-02");

        // 3) Intentionally over-consume Vitamin C (will throw but continue)
        safeAttempt(() ->
            movementService.createMovement("MED-002", MovementType.RECEIVE_MEDICINE.name(), 50, LocalDate.of(2025, 10, 3)),
            "Order 50 Vitamin C on 2025-10-03");

        // 4) Deliver 100 Vitamin C, then order 25
        safeAttempt(() ->
            movementService.createMovement("MED-002", MovementType.DELIVER_MEDICINE.name(), 100, LocalDate.of(2025, 10, 3)),
            "Deliver 100 Vitamin C on 2025-10-03");

        safeAttempt(() ->
            movementService.createMovement("MED-002", MovementType.RECEIVE_MEDICINE.name(), 25, LocalDate.of(2025, 10, 4)),
            "Order 25 Vitamin C on 2025-10-04");

        // --- Show all medicines (after movements) ---
        System.out.println("\n== Medicines (after movements) ==");
        printMedicines(medicineService.listAllMedicines());

        // --- Query movements (all) ---
        System.out.println("\n== All movements (by date asc) ==");
        printMovements(movementService.findMovements(Optional.empty(), Optional.empty(), Optional.empty()));

        // --- Query movements filtered by medicine and date range ---
        System.out.println("\n== Movements for MED-002 between 2025-10-03 and 2025-10-04 ==");
        printMovements(movementService.findMovements(
                Optional.of("MED-002"),
                Optional.of(LocalDate.of(2025, 10, 3)),
                Optional.of(LocalDate.of(2025, 10, 4))
        ));
    }
    private static void safeAttempt(Runnable action, String label) {
        try {
            action.run();
            System.out.println("[OK] " + label);
        } catch (Exception ex) {
            System.out.println("[FAILED] " + label + " -> " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
    private static void printMedicines(List<Medicine> meds) {
        for (Medicine m : meds) {
            System.out.printf(" - %s (%s) | price=%s | stock=%d | category=%s%n",
                    m.getName(), m.getCode(), m.getPrice(), m.getStock(), m.getCategory().getmedCategory());
        }
    }

    private static void printMovements(List<WarehouseMovement> mvs) {
        for (WarehouseMovement mv : mvs) {
            System.out.printf(" - %s | med=%s | amount=%d | date=%s%n",
                    mv.getType(), mv.getMedicine().getCode(), mv.getAmount(), mv.getTimestamp());
        }
    }
}
