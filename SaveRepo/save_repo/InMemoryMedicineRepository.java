package SaveRepo.save_repo;
// imports
import MedicalSupplySystem.medical_supply_system.*;
import SaveRepo.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class InMemoryMedicineRepository implements MedicineRepository {
    private final Map<Long, Medicine> data = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override public Medicine save(Medicine m) {
        Long id = (m.getId() == null) ? seq.getAndIncrement() : m.getId();
        Medicine stored = new Medicine(id, m.getCode(), m.getName(), m.getPrice(), m.getStock(), m.getCategory());
        data.put(id, stored);
        return stored;
    }
    @Override public Optional<Medicine> findById(Long id){ return Optional.ofNullable(data.get(id)); }
    @Override public Optional<Medicine> findByCode(String code){
        return data.values().stream().filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst();
    }
    @Override public List<Medicine> findAll(){ return new ArrayList<>(data.values()); }
}
