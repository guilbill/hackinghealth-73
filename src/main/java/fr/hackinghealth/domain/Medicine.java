package fr.hackinghealth.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Medicine.
 */
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;
    
    @NotNull
    @Column(name = "cip", nullable = false)
    private String cip;
    
    @Column(name = "vidal_id")
    private Long vidalId;
    
    @Column(name = "number_of_pills")
    private Integer numberOfPills;
    
    @ManyToOne
    @JoinColumn(name = "medicine_chest_id")
    private MedicineChest medicineChest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCip() {
        return cip;
    }
    
    public void setCip(String cip) {
        this.cip = cip;
    }

    public Long getVidalId() {
        return vidalId;
    }
    
    public void setVidalId(Long vidalId) {
        this.vidalId = vidalId;
    }

    public Integer getNumberOfPills() {
        return numberOfPills;
    }
    
    public void setNumberOfPills(Integer numberOfPills) {
        this.numberOfPills = numberOfPills;
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
        Medicine medicine = (Medicine) o;
        if(medicine.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Medicine{" +
            "id=" + id +
            ", designation='" + designation + "'" +
            ", cip='" + cip + "'" +
            ", vidalId='" + vidalId + "'" +
            ", numberOfPills='" + numberOfPills + "'" +
            '}';
    }
}
