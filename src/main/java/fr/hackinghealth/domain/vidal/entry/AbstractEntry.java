package fr.hackinghealth.domain.vidal.entry;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

import fr.hackinghealth.domain.vidal.Response.Link;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public abstract class AbstractEntry {
    public static final String NS_VIDAL = "http://api.vidal.net/-/spec/vidal-api/1.0/";
    static final String NS_ATOM = "http://www.w3.org/2005/Atom";
    
    @XmlElement(namespace = NS_VIDAL, name = "id")
    Long idVidal;
    
    @XmlElementRefs({ @XmlElementRef(name = "link", namespace = NS_ATOM, type = Link.class, required = false) })
    private List<Link> links;
    
    /**
     * @return the idVidal
     */
    public Long getIdVidal() {
        return idVidal;
    }
}
