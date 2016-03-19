package fr.hackinghealth.domain.vidal.securisation.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name="Allergies", propOrder="allergy")
public class Allergies {
	@XmlElement
    private List<String> allergy;
    
    public List<String> getAllergy() {
        if (allergy == null) {
        	allergy = new ArrayList<>();
        }
        return allergy;
    }
}
