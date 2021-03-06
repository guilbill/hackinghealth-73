@XmlSchema(
        namespace = AbstractResponse.NS_ATOM,
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = AbstractResponse.NS_ATOM),
                @XmlNs(prefix = "opensearch", namespaceURI = AbstractResponse.NS_OPENSEARCH),
                @XmlNs(prefix = "vidal", namespaceURI = AbstractEntry.NS_VIDAL)
        })
package fr.hackinghealth.domain.vidal.Response;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;

import fr.hackinghealth.domain.vidal.Response.AbstractResponse;
import fr.hackinghealth.domain.vidal.entry.AbstractEntry;

