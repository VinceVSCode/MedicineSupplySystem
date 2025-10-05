// MedicineCategory.java
package MedicalSupplySystem.medical_supply_system;

public class MedicineCategory {
    private long id;
    private String medCategory;

    public MedicineCategory(long id, String medCategory) {
        this.id = id;
        this.medCategory = medCategory;
    }

    public Long getId() {
        return id;
    }

    public String getmedCategory() {
        return medCategory;
    }
}