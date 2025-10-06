
package SaveRepo.save_repo;

import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryMedicineCategoryRepository implements MedicineCategoryRepository {
    private final Map<Long, MedicineCategory> data = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override public MedicineCategory save(MedicineCategory c) {
        Long id = (c.getId() == null) ? seq.getAndIncrement() : c.getId();
        MedicineCategory stored = new MedicineCategory(id, c.getmedCategory());
        data.put(id, stored);
        return stored;
    }
    @Override public Optional<MedicineCategory> findById(Long id){ return Optional.ofNullable(data.get(id)); }
    @Override public Optional<MedicineCategory> findByName(String name){
        return data.values().stream().filter(x -> x.getmedCategory().equalsIgnoreCase(name)).findFirst();
    }
    @Override public List<MedicineCategory> findAll(){ return new ArrayList<>(data.values()); }
}
