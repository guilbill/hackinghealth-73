package fr.hackinghealth.domain.vidal.securisation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import fr.hackinghealth.domain.vidal.Response.AbstractResponse;

@XmlRootElement(name="feed")
public class ReponseSecurisation extends AbstractResponse{
	@XmlElement(type = PrescriptionLine.class)
    @XmlPath("entry[@vidal:categories='PRESCRIPTION_LINE']")
    private List<PrescriptionLine> prescriptions;
    
    @XmlElement(type = PrescriptionLineAlert.class)
    @XmlPath("entry[@vidal:categories='ALERT']")
    private List<PrescriptionLineAlert> alerts;
    
    @XmlElement(type = Patient.class)
    @XmlPath("entry[@vidal:categories='PATIENT']")
    private Patient patient;
    
    public ReponseSecurisation() {
        super();
        prescriptions = new ArrayList<PrescriptionLine>();
        alerts = new ArrayList<PrescriptionLineAlert>();
    }

	public List<PrescriptionLine> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<PrescriptionLine> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<PrescriptionLineAlert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<PrescriptionLineAlert> alerts) {
		this.alerts = alerts;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
 
	
}
