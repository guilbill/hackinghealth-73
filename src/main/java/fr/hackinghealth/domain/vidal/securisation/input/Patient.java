package fr.hackinghealth.domain.vidal.securisation.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {

	@XmlElement(name="creatin")
	private Integer creatin;
	
	@XmlElement(name="dateOfBirth")
	private String dateOfBirth;
	
	@XmlElement(name="gender")
	private Gender gender;
	
	@XmlElement(name="height")
	private String height;
	
	@XmlElement(name="weight")
	private String weight;
	
	@XmlElement(name="breastFeeding")
	private BreastFeeding breastFeeding;
	
	@XmlElement(name="hepaticInsufficiency")
	private HepaticInsufficiency hepaticInsufficiency;
	
	@XmlElement(name="weeksOfAmenorrhea")
	private Integer weeksOfAmenorrhea;
	
	private Allergies allergies;
	
	private Molecules molecules;
	
	private Pathologies pathologies;


	public static enum Gender {
		MALE,
		FEMALE,
		UNKNOWN;
	}
	
	public static enum BreastFeeding {
		NONE,
		LESS_THAN_ONE_MONTH,
		MORE_THAN_ONE_MONTH,
		ALL;
	}
	
	public static enum HepaticInsufficiency {
		NONE, 
		MODERATE, 
		SEVERE;
	}

	
	
	

	public Allergies getAllergies() {
		return allergies;
	}

	public void setAllergies(Allergies allergies) {
		this.allergies = allergies;
	}

	public Molecules getMolecules() {
		return molecules;
	}

	public void setMolecules(Molecules molecules) {
		this.molecules = molecules;
	}

	public Pathologies getPathologies() {
		return pathologies;
	}

	public void setPathologies(Pathologies pathologies) {
		this.pathologies = pathologies;
	}

	public Integer getCreatin() {
		return creatin;
	}

	public void setCreatin(Integer creatin) {
		this.creatin = creatin;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public BreastFeeding getBreastFeeding() {
		return breastFeeding;
	}

	public void setBreastFeeding(BreastFeeding breastFeeding) {
		this.breastFeeding = breastFeeding;
	}

	public HepaticInsufficiency getHepaticInsufficiency() {
		return hepaticInsufficiency;
	}

	public void setHepaticInsufficiency(HepaticInsufficiency hepaticInsufficiency) {
		this.hepaticInsufficiency = hepaticInsufficiency;
	}

	public Integer getWeeksOfAmenorrhea() {
		return weeksOfAmenorrhea;
	}

	public void setWeeksOfAmenorrhea(Integer weeksOfAmenorrhea) {
		this.weeksOfAmenorrhea = weeksOfAmenorrhea;
	}
	
	

	
}
