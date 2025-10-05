package MedicalSupplySystem.medical_supply_system;
import java.time.LocalDate;
// imports
import java.time.LocalDateTime;

public class WarehouseMovement {

    private long id;
    private MovementType mType;
    private Medicine medicine;
    private int amount;
    private LocalDate  date;

    public WarehouseMovement(long id, MovementType mType, Medicine medicine, int amount, LocalDate  date) {
        this.id = id;
        this.mType = mType;
        this.medicine = medicine;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public MovementType getMovementType() {
        return mType;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return amount;
    }

    public LocalDate  getTimestamp() {
        return date;
    }
}
