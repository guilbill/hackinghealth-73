package fr.hackinghealth.domain.vidal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.hackinghealth.domain.vidal.Response.AbstractResponse;

@XmlRootElement(name = "feed")
public class MonographieReponse extends AbstractResponse {
	@XmlElement(name = "entry")
	private Document document;

	public Document getDocument() {
		return document;
	}

}
