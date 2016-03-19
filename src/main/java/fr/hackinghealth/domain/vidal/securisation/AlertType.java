package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

public class AlertType {
	 private static final Logger LOGGER = Logger.getLogger(Trigger.class);
    
	 private VidalAlertType type = VidalAlertType.UNREFERENCED;
    
    @XmlValue
    private String libelle;
    
    public static enum VidalAlertType {
        UNREFERENCED,
        UNSECURIZED,
        PRECAUTION,
        CONTRA_INDICATION,
        ALLERGY,
        POSOLOGY,
        SIDE_EFFECT,
        WARNING,
        SURVEILLANCE,
        PHYSICO_CHEMICAL_INTERACTION,
        REDUNDANT_ACTIVE_INGREDIENT,
        DRUG_INTERACTION,
        SAME_DRUG,
        INDICATOR;
    }
    
    
    
    public VidalAlertType getType() {
		return type;
	}



	public void setType(VidalAlertType type) {
		this.type = type;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@XmlAttribute(name = "name")
    public void setVidalAlertType(final String name) {
        if (!Strings.isNullOrEmpty(name)) {
            try {
                VidalAlertType iType = VidalAlertType.valueOf(name);
                if (iType != null) {
                    type = iType;
                }
            } catch (IllegalArgumentException e) {
                LOGGER.info("VidalAlertType non référencé : " + name);
            }
        }
    }
 
}
