package SaveRepo.save_repo;

// imports
import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryWarehouseMovementRepository implements WarehouseMovementRepository {
    private final Map<Long, WarehouseMovement> data = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override public WarehouseMovement save(WarehouseMovement mv) {
        Long id = (mv.getId() == null) ? seq.getAndIncrement() : mv.getId();
        WarehouseMovement stored = new WarehouseMovement(id, mv.getType(), mv.getMedicine(), mv.getAmount(), mv.getTimestamp());
        data.put(id, stored);
        return stored;
    }
    @Override public List<WarehouseMovement> findAll(){ return new ArrayList<>(data.values()); }

    @Override public List<WarehouseMovement> findByFilter(Optional<String> medicineCode,
                                                           Optional<LocalDate> dateFrom,
                                                           Optional<LocalDate> dateTo) {
        return data.values().stream().filter(mv -> {
            boolean ok = true;
            if (medicineCode.isPresent()) {
                ok &= mv.getMedicine().getCode().equalsIgnoreCase(medicineCode.get());
            }
            if (dateFrom.isPresent()) {
                ok &= !mv.getTimestamp().isBefore(dateFrom.get());
            }
            if (dateTo.isPresent()) {
                ok &= !mv.getTimestamp().isAfter(dateTo.get());
            }
            return ok;
        }).sorted(Comparator.comparing(WarehouseMovement::getTimestamp))
          .collect(Collectors.toList());
    }
}


