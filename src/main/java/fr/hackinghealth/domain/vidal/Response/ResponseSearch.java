/**
 * (C)Pharmagest Interactive<br/>
 */
package fr.hackinghealth.domain.vidal.Response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import fr.hackinghealth.domain.vidal.entry.Package;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseSearch
extends AbstractResponse {
    
    @XmlElement(type = Package.class)
    @XmlPath("entry[@vidal:categories='PACKAGE']")
    List<Package> packages;
    
    /**
     * @return the packages
     */
    public List<Package> getPackages() {
        return packages;
    }
    
}
