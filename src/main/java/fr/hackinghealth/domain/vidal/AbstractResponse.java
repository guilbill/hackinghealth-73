package fr.hackinghealth.domain.vidal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

@XmlAccessorType(XmlAccessType.NONE)
public class AbstractResponse {

	private static final String NS_ATOM = "http://www.w3.org/2005/Atom";
	private static final String NS_OPENSEARCH= "http://a9.com/-/spec/opensearch/1.1/";
	
	
	@XmlElement(required = true)
	private String title;
	
	
	@XmlElement(namespace = NS_OPENSEARCH, name="startIndex" )
	private Long startIndex;
	
	@XmlElement(namespace=NS_OPENSEARCH, name="itemsPerPage")
	private Long itemsPerPage;
	
	@XmlElement(namespace=NS_OPENSEARCH , name="totalResults")
	private Long totalResults;
	
	
	@XmlElementRefs({@XmlElementRef(name = "link", namespace = NS_ATOM, type = Link.class, required = false)})
	private List<Link> links;


	public AbstractResponse() {
		super();
		links = new ArrayList<>();
	}

	
	

    
   
    
	
}
