package fr.hackinghealth.domain.vidal.entry;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import fr.hackinghealth.domain.vidal.Category;
import fr.hackinghealth.domain.vidal.response.Link;
import fr.hackinghealth.domain.vidal.response.Link.LinkType;

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
	    @XmlElement
	    private String title;
	    
	    private ListMultimap<LinkType, Link> links;
	    
	    @XmlElement
	    private String id;
	    
	    @XmlElement
	    private String summary;
	    
	    @XmlElement
	    private Category category;
	    
    @XmlElement(name = "title")
    String title;
	    
	    @XmlElement(namespace = NS_VIDAL, name = "id")
	    private Long idVidal;
	    public AbstractEntry() {
	        super();
	    }
	    
	    public List<Link> getLinks(final LinkType linkType) {
	        return links.get(linkType);
	    }
	    
	    @XmlElementRefs({
	    })
	    public void setLinkList(final List<Link> links) {
	        if (this.links == null) {
	            this.links = ArrayListMultimap.create();
	        }
	        for (Link link : links) {
	            if (!LinkType.UNREFERENCED.equals(link.getCategoryLink())) {
	                this.links.put(link.getCategoryLink(), link);
	            }
	        }
	        if (this.links.isEmpty()) {
	            this.links = null;
	        }
	    }
	    
	        if (this.links == null) {
	            this.links = ArrayListMultimap.create();
	        }
	        return new ArrayList<Link>(links.values());
	    }
	    public AbstractEntry(final String id, final String title, final String summary) {
	        super();
	        this.id = id;
	        this.title = title;
	        this.summary = summary;
	    }
	    
	    public String getCategorie() {
	        if (category == null) {
	            return null;
	        }
	        return category.getTerm();
	    }
		
		/**
     * @return the idVidal
     */
    public Long getIdVidal() {
        return idVidal;
    }
}
