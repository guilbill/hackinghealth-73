package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

@XmlAccessorType(XmlAccessType.NONE)
public 	class Trigger {
	    private static final Logger LOGGER = Logger.getLogger(Trigger.class);
	    @XmlAttribute(name = "id")
	    private Integer id;
	    
	    @XmlValue
	    private String content;
	    
	    private TriggerType type = TriggerType.UNREFERENCED;
	    
	    public enum TriggerType {
	        UNREFERENCED,
	        MOLECULE,
	        ALLERGY,
	        AGE,
	        WEIGHT,
	        PREGNANT,
	        BREAST_FEEDING,
	        CREATIN_CLEARANCE,
	        GENDER,
	        CIM10,
	        PRODUCT,
	        PACK,
	        COMMON_NAME_GROUP,
	        UCD,
	        UNKNOWN,
	        ACCESSORY_RANGE,
	        BRANDED_PRESCRIBABLE,
	        NO_POSOLOGY_FOR_THIS_PRODUCT,
	        NO_POSOLOGY_FOR_THESE_INDICATIONS,
	        NO_POSOLOGY_FOR_THESE_ROUTES,
	        NO_POSOLOGY_FOR_THIS_PROFILE,
	        NO_POSOLOGY_FOR_THIS_GENDER;
	    }
	    
	    @XmlAttribute(name = "type")
	    public void setTriggerType(final String name) {
	        if (!Strings.isNullOrEmpty(name)) {
	            try {
	                TriggerType iType = TriggerType.valueOf(name);
	                if (iType != null) {
	                    type = iType;
	                }
	            } catch (IllegalArgumentException e) {
	                LOGGER.info("TriggerType non référencé : " + name);
	            }
	        }
	    }
	    
	   
	 
}
