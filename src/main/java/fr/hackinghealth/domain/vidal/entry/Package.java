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
public class Package
        extends AbstractEntry {
    @XmlElement(namespace = NS_VIDAL, name = "name")
    private String name;
    
}
