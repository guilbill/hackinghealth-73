package fr.hackinghealth.domain.vidal.Response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Strings;


@XmlRootElement(name="link")
@XmlAccessorType(XmlAccessType.NONE)
public class Link {
	 private String title;
	 
	 
	private static Logger LOGGER = Logger.getLogger(Link.class);
	
	@XmlAttribute(name="href", required = true)
	private String href;
	
	 
    @XmlAttribute(name = "type")
	private String type;
	
	 private LinkType categoryLink = LinkType.NONE;
	 
	private String rel;
	
	private RelationType relationType = RelationType.UNREFERENCED;
	
	
	
	public static enum RelationType {
	       UNREFERENCED, INLINE, RELATED, ALTERNATE, SELF, PREVIOUS, NEXT;
	}
	
	
	public enum LinkType {
        UNREFERENCED, NONE, PACKAGES, PRODUCTS, VMPS, SUBSTITUTE_PACKAGE, CONTRAINDICATION, CHILDREN,
        SAUMON_CLASSIFICATION, OPT_DOCUMENT_PATIENT, EPHMRA, PRODUCT, OPT_DOCUMENT, DOCUMENT, MOLECULES,
        ACTIVE_EXCIPIENTS, RECOS, FOREIGN_PRODUCTS, INDICATIONS, DOCUMENTS, EUREKA_SANTE, ATC_CLASSIFICATION,
        SMR_ASMR, SMR_ASMR_HTML, ALD_DETAIL, GENERIC_GROUPS, VIDAL_CLASSIFICATION, PRODUCT_RANGE, RANGE_PRODUCTS,
        VMP, UCDS, UNITS, LPPR, MONO, VDF, RCP_VIDAL, POSOLOGY, PDS, HISTORY, Previous_page, Next_page, Current_page,
        PDS_PARENT, PDS_CHILDREN, CODES, INDICATORS, RESTRICTED_PRESCRIPTIONS, PARENT,
        LARGER_PACKS, AFFILIATION_CENTER, PRICING_SCHEDULE, STORAGE, CONTAINER;
    }
	
	
	@XmlAttribute(name = "title")
    public void setCategoryLinkFromString(final String title) {
        this.title = title.replace(' ', '_');
        if (StringUtils.isBlank(this.title)) {
            categoryLink = LinkType.NONE;
            return;
        }
        try {
            LinkType linkType = LinkType.valueOf(this.title);
            if (linkType != null) {
                categoryLink = linkType;
            }
        } catch (IllegalArgumentException e) {
            categoryLink = LinkType.UNREFERENCED;
            LOGGER.debug("Catégorie de lien non référencée : " + this.title);
        }
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

	public LinkType getCategoryLink() {
		return categoryLink;
	}
	
	
}
