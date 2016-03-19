/**
 * (C)Pharmagest Interactive<br/>
 */
package fr.hackinghealth.domain.vidal.entry;

import javax.xml.bind.annotation.XmlElement;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
public abstract class AbstractEntry {
    static final String NS_VIDAL = "http://api.vidal.net/-/spec/vidal-api/1.0/";
    
    @XmlElement(name = "title")
    String title;
    
    @XmlElement(namespace = NS_VIDAL, name = "id")
    Long idVidal;
}
