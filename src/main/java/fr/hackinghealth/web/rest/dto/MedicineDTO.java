package fr.hackinghealth.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Medicine entity.
 */
public class MedicineDTO implements Serializable {

    private Long id;

    @NotNull
    private String designation;


    @NotNull
    private String cip;


    private Long vidalId;


    private Integer numberOfPills;


    private Long medicineChestId;
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

    public Long getMedicineChestId() {
        return medicineChestId;
    }

    public void setMedicineChestId(Long medicineChestId) {
        this.medicineChestId = medicineChestId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedicineDTO medicineDTO = (MedicineDTO) o;

        if ( ! Objects.equals(id, medicineDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
            "id=" + id +
            ", designation='" + designation + "'" +
            ", cip='" + cip + "'" +
            ", vidalId='" + vidalId + "'" +
            ", numberOfPills='" + numberOfPills + "'" +
            '}';
    }
}
