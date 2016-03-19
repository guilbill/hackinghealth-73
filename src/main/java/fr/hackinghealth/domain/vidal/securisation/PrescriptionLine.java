package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import fr.hackinghealth.domain.vidal.entry.AbstractEntry;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class PrescriptionLine extends AbstractEntry{

	@XmlElement(namespace = NS_VIDAL, name = "safetyAlert")
    private Boolean safetyAlert;
    
    
    @XmlElement(namespace = NS_VIDAL, name = "code")
    private String code;
    
    @XmlElement(name = "drugId")
    private String drugId;
    @XmlElement(name = "drugType")
    private DrugType drugType;
    
    
    
    public Boolean getSafetyAlert() {
		return safetyAlert;
	}



	public void setSafetyAlert(Boolean safetyAlert) {
		this.safetyAlert = safetyAlert;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
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



	public static enum DrugType {
        COMMON_NAME_GROUP,
        PRODUCT,
        PACK,
        UCD,
        UNKNOWN,
        ACCESSORY_RANGE,
        BRANDED_PRESCRIBABLE;
    }

}
