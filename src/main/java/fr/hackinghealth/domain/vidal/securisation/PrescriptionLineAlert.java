package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import fr.hackinghealth.domain.vidal.entry.AbstractEntry;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class PrescriptionLineAlert extends AbstractEntry{

	    
	    @XmlElement(namespace = NS_VIDAL, name = "severity")
	    private Severity severity;
	    
	    @XmlElement(namespace = NS_VIDAL, name = "alertType")
	    private AlertType alertType;
	    
	    @XmlElement(namespace = NS_VIDAL, name = "triggeredBy")
	    private Trigger trigger;

		public Severity getSeverity() {
			return severity;
		}

		public void setSeverity(Severity severity) {
			this.severity = severity;
		}

		public AlertType getAlertType() {
			return alertType;
		}

		public void setAlertType(AlertType alertType) {
			this.alertType = alertType;
		}

		public Trigger getTrigger() {
			return trigger;
		}

		public void setTrigger(Trigger trigger) {
			this.trigger = trigger;
		}
	    
	    
}
