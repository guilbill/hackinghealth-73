package fr.hackinghealth.domain.vidal.Response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import fr.hackinghealth.domain.vidal.entry.PackageVidal;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "feed")
public class ResponseSearch
extends AbstractResponse {
    
    @XmlElement(type = PackageVidal.class)
    @XmlPath("entry[@vidal:categories='PACKAGE']")
    protected List<PackageVidal> packages;
    
    public ResponseSearch() {
        super();
        packages = new ArrayList<>();
    }
    
    /**
     * @return the packages
     */
    public List<PackageVidal> getPackages() {
        return packages;
    }
    
}
