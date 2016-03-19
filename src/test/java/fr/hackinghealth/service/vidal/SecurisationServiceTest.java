package fr.hackinghealth.service.vidal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import fr.hackinghealth.domain.vidal.securisation.ReponseSecurisation;
import fr.hackinghealth.domain.vidal.securisation.input.Patient;
import fr.hackinghealth.domain.vidal.securisation.input.Patient.BreastFeeding;
import fr.hackinghealth.domain.vidal.securisation.input.Patient.Gender;
import fr.hackinghealth.domain.vidal.securisation.input.Patient.HepaticInsufficiency;
import fr.hackinghealth.domain.vidal.securisation.input.Prescription;
import fr.hackinghealth.domain.vidal.securisation.input.PrescriptionLine;
import fr.hackinghealth.domain.vidal.securisation.input.PrescriptionLine.DrugType;
import fr.hackinghealth.domain.vidal.securisation.input.PrescriptionLines;
import fr.hackinghealth.service.JerseyClientFactory;

public class SecurisationServiceTest {

	
	private PrescriptionLine createLine(String drugId, DrugType drugType ) {
		
		final PrescriptionLine line = new PrescriptionLine();
		line.setDrugCode(String.format("vidal://package/cip13/%s", drugId));

		return line;
	}
	
	@Test
	public void testSecurisation() throws JAXBException {
		
		JerseyClientFactory factory = new JerseyClientFactory("http://apirest-dev.vidal.fr/rest/","9fd557d3","06cbdb4357601927c22dc87d4ea469bc",1000);
		SecurisationService fixture = factory.getClient(SecurisationService.class);
		
		
		final Patient patient = new Patient();
		
		patient.setCreatin(80);
		patient.setDateOfBirth("2011-12-02T15:11:35.095+01:00");
		patient.setGender(Gender.MALE);
		patient.setHeight("180");
		patient.setWeight("80");
		patient.setBreastFeeding(BreastFeeding.NONE);
		patient.setHepaticInsufficiency(HepaticInsufficiency.SEVERE);
		
		final Prescription prescription = new Prescription();
		prescription.setPatient(patient);
		
		PrescriptionLines lines = new PrescriptionLines();
		lines.getLine().add(createLine("3400932320189",DrugType.PACK));
		
		
		prescription.setLines(lines);
		
		
		final JAXBContext context = JAXBContext.newInstance(Prescription.class);
		
		final Marshaller marshall = context.createMarshaller();
		marshall.marshal(prescription, System.out );
		
		
		ReponseSecurisation reponse = fixture.securisation("9fd557d3","06cbdb4357601927c22dc87d4ea469bc",prescription);
		
		
		Assertions.assertThat(reponse).isNotNull();
	}

}
