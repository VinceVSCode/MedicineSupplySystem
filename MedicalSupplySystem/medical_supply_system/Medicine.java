// Medicine.java
// edw exoume thn class Medicine
package MedicalSupplySystem.medical_supply_system;
// imports
import java.math.BigDecimal;

public class Medicine {

    private long id;
    private String code;
    private String name;
    // BigDecimal gia extra akribeia.
    private BigDecimal price;
    private int stock;
    private MedicineCategory category;

    public Medicine(Long id, String code, String name, BigDecimal price, int stock, MedicineCategory category) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public MedicineCategory getCategory() {
        return category;
    }
    // methods to adjust stock
    public void increaseStock(int amount) { this.stock += amount; }
    public void decreaseStock(int amount) { this.stock -= amount; }
}
