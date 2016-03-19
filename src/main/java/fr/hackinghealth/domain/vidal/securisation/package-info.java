@XmlSchema(
        namespace = "http://www.w3.org/2005/Atom",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://www.w3.org/2005/Atom"),
                @XmlNs(prefix = "opensearch", namespaceURI = AbstractResponse.NS_OPENSEARCH),
                @XmlNs(prefix = "vidal", namespaceURI = "http://api.vidal.net/-/spec/vidal-api/1.0/")
        })
package fr.hackinghealth.domain.vidal.securisation;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

import fr.hackinghealth.domain.vidal.AbstractResponse;

