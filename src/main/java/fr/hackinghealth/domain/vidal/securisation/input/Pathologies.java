package fr.hackinghealth.domain.vidal.securisation.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Pathologies", propOrder="pathologie")
public class Pathologies {

	
	@XmlElement(name = "pathology")
    private List<String> pathologie;
    
    public List<String> getPathologies() {
        if (pathologie == null) {
            pathologie = new ArrayList<>();
        }
        return pathologie;
    }
}
