package fr.hackinghealth.domain.vidal.securisation.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionLine {
	
	@XmlElement
    private String drugId;
	
    @XmlElement
    private DrugType drugType;
    
    @XmlElement(name = "drug")
    private String drugCode;
    
    
    public static enum DrugType {
        COMMON_NAME_GROUP,
        PRODUCT,
        PACK,
        UCD,
        UNKNOWN,
        ACCESSORY_RANGE,
        BRANDED_PRESCRIBABLE;
    }
    
    public String getDrugId() {
		return drugId;
	}



	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}



	public DrugType getDrugType() {
		return drugType;
	}



	public void setDrugType(DrugType drugType) {
		this.drugType = drugType;
	}



	public String getDrugCode() {
		return drugCode;
	}



	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}



	
}
