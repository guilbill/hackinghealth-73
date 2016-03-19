package fr.hackinghealth.domain.vidal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;


@XmlRootElement(name="link")
@XmlAccessorType(XmlAccessType.NONE)
public class Link {

	private static Logger LOGGER = Logger.getLogger(Link.class);
	
	@XmlAttribute(name="href", required = true)
	private String href;
	
	@XmlAttribute(name="type", required = true)
	private String type;
	
	
	private String rel;
	
	private RelationType relationType = RelationType.UNREFERENCED;
	
	
	
	public static enum RelationType {
	       UNREFERENCED, INLINE, RELATED, ALTERNATE, SELF, PREVIOUS, NEXT;
	}
	
	
	@XmlAttribute(name = "rel", required = true)
    public void setRelType(final String rel) {
        this.rel = rel;
        if (!Strings.isNullOrEmpty(this.rel)) {
            try {
                RelationType rType = RelationType.valueOf(this.rel.toUpperCase());
                if (rType != null) {
                    relationType = rType;
                }
            } catch (IllegalArgumentException e) {
            	LOGGER.debug("RelType de lien non référencée : " + this.rel);
            }
        }
    }


	public RelationType getRelationType() {
		return relationType;
	}
	
	
}
