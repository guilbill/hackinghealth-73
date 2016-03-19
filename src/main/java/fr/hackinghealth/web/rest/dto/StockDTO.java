package fr.hackinghealth.web.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Stock entity.
 */
public class StockDTO implements Serializable {

    private Long id;

    @NotNull
    private String batchNumber;


    @NotNull
    private Double numberOfPills;


    @NotNull
    private LocalDate expirationDate;


    private Long medicineId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
    public Double getNumberOfPills() {
        return numberOfPills;
    }

    public void setNumberOfPills(Double numberOfPills) {
        this.numberOfPills = numberOfPills;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StockDTO stockDTO = (StockDTO) o;

        if ( ! Objects.equals(id, stockDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StockDTO{" +
            "id=" + id +
            ", batchNumber='" + batchNumber + "'" +
            ", numberOfPills='" + numberOfPills + "'" +
            ", expirationDate='" + expirationDate + "'" +
            '}';
    }
}
