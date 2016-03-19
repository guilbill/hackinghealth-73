package fr.hackinghealth.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MedicineChest.
 */
@Entity
@Table(name = "medicine_chest")
public class MedicineChest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "open", nullable = false)
    private Boolean open;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return open;
    }
    
    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicineChest medicineChest = (MedicineChest) o;
        if(medicineChest.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, medicineChest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MedicineChest{" +
            "id=" + id +
            ", open='" + open + "'" +
            '}';
    }
}
