package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://api.vidal.net/-/spec/vidal-api/1.0/", name = "Patient")
@XmlAccessorType(XmlAccessType.NONE)
public class Patient {
    @XmlElement(name = "dateOfBirth")
    private String dateOfBirth;
    
    @XmlElement(name = "gender")
    private Gender gender;
    
    @XmlElement(name = "weight")
    private Double weight;
    
    @XmlElement(name = "height")
    private Double height;
    
    @XmlElement(name = "weeksOfAmenorrhea")
    private Integer weeksOfAmenorrhea;
    
    @XmlElement(name = "breastFeeding")
    private Breastfeeding breastFeeding;
    
    @XmlElement(name = "creatin")
    private Integer creatin;
    
    @XmlElement(name = "hepaticInsufficiency")
    private HepaticInsufficiency hepaticInsufficiency;
    
    public static enum Gender {
        MALE,
        FEMALE,
        UNKNOWN;
    }
    
    public static enum Breastfeeding {
        NONE,
        LESS_THAN_ONE_MONTH,
        MORE_THAN_ONE_MONTH,
        ALL
    }
    
    public static enum HepaticInsufficiency {
        NONE,
        MODERATE,
        SEVERE;
    }

}
