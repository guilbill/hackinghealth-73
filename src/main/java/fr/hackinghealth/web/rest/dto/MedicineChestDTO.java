package fr.hackinghealth.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the MedicineChest entity.
 */
public class MedicineChestDTO implements Serializable {

    private Long id;

    @NotNull
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

        MedicineChestDTO medicineChestDTO = (MedicineChestDTO) o;

        if ( ! Objects.equals(id, medicineChestDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MedicineChestDTO{" +
            "id=" + id +
            ", open='" + open + "'" +
            '}';
    }
}
