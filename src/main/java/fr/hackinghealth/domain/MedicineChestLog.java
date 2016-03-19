package fr.hackinghealth.domain;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MedicineChestLog.
 */
@Entity
@Table(name = "medicine_chest_log")
public class MedicineChestLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @NotNull
    @Column(name = "open", nullable = false)
    private Boolean open;
    
    @ManyToOne
    @JoinColumn(name = "medicine_chest_id")
    private MedicineChest medicineChest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getOpen() {
        return open;
    }
    
    public void setOpen(Boolean open) {
        this.open = open;
    }

    public MedicineChest getMedicineChest() {
        return medicineChest;
    }

    public void setMedicineChest(MedicineChest medicineChest) {
        this.medicineChest = medicineChest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicineChestLog medicineChestLog = (MedicineChestLog) o;
        if(medicineChestLog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, medicineChestLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MedicineChestLog{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", open='" + open + "'" +
            '}';
    }
}
