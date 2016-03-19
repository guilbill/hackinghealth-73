package fr.hackinghealth.domain.vidal.securisation.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name="Molecules", propOrder="molecule")
public class Molecules {
	@XmlElement
    private List<String> molecule;
    
    public List<String> getMolecules() {
        if (molecule == null) {
        	molecule = new ArrayList<>();
        }
        return molecule;
    }
}
