package fr.hackinghealth.domain.vidal.securisation.input;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import fr.hackinghealth.domain.vidal.securisation.input.Patient.BreastFeeding;
import fr.hackinghealth.domain.vidal.securisation.input.Patient.Gender;
import fr.hackinghealth.domain.vidal.securisation.input.Patient.HepaticInsufficiency;
import fr.hackinghealth.domain.vidal.securisation.input.PrescriptionLine.DrugType;

public class PrescriptionTest {

	
	private PrescriptionLine createLine(String drugId, DrugType drugType ) {
	
		final PrescriptionLine line = new PrescriptionLine();
		
		line.setDrugId(drugId);
		line.setDrugType(drugType);
		return line;
	}
	
	
	@Test
	public void test_serialization() throws JAXBException {
		final Patient patient = new Patient();
		
		patient.setCreatin(80);
		patient.setDateOfBirth("2011-12-02T15:11:35.095+01:00");
		patient.setGender(Gender.MALE);
		patient.setHeight("180");
		patient.setWeight("80");
		patient.setBreastFeeding(BreastFeeding.NONE);
		patient.setHepaticInsufficiency(HepaticInsufficiency.SEVERE);
		
		
		final StringWriter sw = new StringWriter();
		
		
		final Prescription prescription = new Prescription();
		prescription.setPatient(patient);
		
		PrescriptionLines lines = new PrescriptionLines();
		lines.getLine().add(createLine("100",DrugType.PACK));
		lines.getLine().add(createLine("101",DrugType.PACK));
		
		prescription.setLines(lines);
		final JAXBContext context = JAXBContext.newInstance(Prescription.class);
		
		final Marshaller marshall = context.createMarshaller();
		marshall.marshal(prescription, sw );
		System.out.println(sw.toString());
		
	}

}
