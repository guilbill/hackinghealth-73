package fr.hackinghealth.domain.vidal.securisation.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import fr.hackinghealth.domain.vidal.entry.AbstractEntry;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Prescription")
@XmlRootElement(name = "prescription")
public class Prescription extends AbstractEntry{
    
    @XmlElement
    private Patient patient;
    
    @XmlElement(name = "prescription-lines")
    private PrescriptionLines lines;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PrescriptionLines getLines() {
		return lines;
	}

	public void setLines(PrescriptionLines lines) {
		this.lines = lines;
	}

    
    
}
