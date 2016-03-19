package fr.hackinghealth.domain;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "batch_number", nullable = false)
    private String batchNumber;
    
    @NotNull
    @Column(name = "number_of_pills", nullable = false)
    private Double numberOfPills;
    
    @NotNull
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        if(stock.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id=" + id +
            ", batchNumber='" + batchNumber + "'" +
            ", numberOfPills='" + numberOfPills + "'" +
            ", expirationDate='" + expirationDate + "'" +
            '}';
    }
}
