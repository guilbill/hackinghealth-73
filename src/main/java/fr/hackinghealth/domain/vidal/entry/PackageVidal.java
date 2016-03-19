package fr.hackinghealth.domain.vidal.entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType
public class PackageVidal
extends AbstractEntry {
    @XmlElement(namespace = NS_VIDAL, name = "name")
    private String name;
    
    @XmlElement(namespace = NS_VIDAL, name = "cip13")
    private String cip13;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the cip13
     */
    public String getCip13() {
        return cip13;
    }
    
}
