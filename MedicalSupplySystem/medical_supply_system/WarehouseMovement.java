package MedicalSupplySystem.medical_supply_system;
// imports
import java.time.LocalDateTime;

public class WarehouseMovement {

    private long id;
    private MovementType mType;
    private Medicine medicine;
    private int quantity;
    private LocalDateTime timestamp;

    public WarehouseMovement(long id, MovementType mType, Medicine medicine, int quantity, LocalDateTime timestamp) {
        this.id = id;
        this.mType = mType;
        this.medicine = medicine;
        this.quantity = quantity;
        this.timestamp = timestamp;
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
        return quantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
