package fr.hackinghealth.domain.vidal;

import javax.xml.bind.annotation.XmlElement;

import fr.hackinghealth.domain.vidal.entry.AbstractEntry;

public class Document extends AbstractEntry{
	@XmlElement(namespace = NS_VIDAL, name = "name")
    private String name;
    
    @XmlElement(namespace = NS_VIDAL, name = "itemType")
    private String itemType;
    
    public Document() {
        super();
    }
    
    public Document(final String id, final String title, final String summary) {
        super(id, title, summary);
    }
}
