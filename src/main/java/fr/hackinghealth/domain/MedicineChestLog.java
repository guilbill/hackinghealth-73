package fr.hackinghealth.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    private ZonedDateTime date;
    
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

    public ZonedDateTime getDate() {
        return date;
    }
    
    public void setDate(ZonedDateTime date) {
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
