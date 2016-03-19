package fr.hackinghealth.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Medication.
 */
@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "cip", nullable = false)
    private String cip;
    
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull
    @Column(name = "lot_number", nullable = false)
    private String lotNumber;
    
    @Column(name = "min_stock")
    private Integer minStock;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCip() {
        return cip;
    }
    
    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getLotNumber() {
        return lotNumber;
    }
    
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Integer getMinStock() {
        return minStock;
    }
    
    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medication medication = (Medication) o;
        if(medication.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, medication.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Medication{" +
            "id=" + id +
            ", cip='" + cip + "'" +
            ", name='" + name + "'" +
            ", lotNumber='" + lotNumber + "'" +
            ", minStock='" + minStock + "'" +
            '}';
    }
}
