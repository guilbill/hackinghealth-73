package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlValue;

public class EtatCommercialisation {
	@XmlEnum
    public enum TypeEtatCommercialisation {
        AVAILABLE,
        DELETED,
        PHARMACO,
        NEW,
        DELETED_ONEYEAR,
        PHARMACO_ONEYEAR;
    }
    
    @XmlAttribute(name = "name")
    TypeEtatCommercialisation typeEtatCommercialisation;
    
    @XmlValue
    String libelle;

	public TypeEtatCommercialisation getTypeEtatCommercialisation() {
		return typeEtatCommercialisation;
	}

	public void setTypeEtatCommercialisation(
			TypeEtatCommercialisation typeEtatCommercialisation) {
		this.typeEtatCommercialisation = typeEtatCommercialisation;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
    
    
}

