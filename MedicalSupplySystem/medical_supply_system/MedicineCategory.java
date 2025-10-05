// MedicineCategory.java
package MedicalSupplySystem.medical_supply_system;

public class MedicineCategory {
    private Long id;
    private String medCategory;

    public MedicineCategory(Long id, String medCategory) {
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
