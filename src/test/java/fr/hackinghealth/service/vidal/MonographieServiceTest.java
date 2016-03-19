package fr.hackinghealth.service.vidal;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import fr.hackinghealth.domain.vidal.MonographieReponse;
import fr.hackinghealth.service.JerseyClientFactory;

public class MonographieServiceTest {

	@Test
	public void testDocumentsOptimaux() {
		JerseyClientFactory factory = new JerseyClientFactory(
				"http://apirest-dev.vidal.fr/rest/", "9fd557d3",
				"06cbdb4357601927c22dc87d4ea469bc", 1000);
		MonographieService monoService = factory
				.getClient(MonographieService.class);

		MonographieReponse reponse = monoService.documentsOptimaux(11038);

		Assertions.assertThat(reponse).isNotNull();
		Assertions.assertThat(reponse.getDocument()).isNotNull();
		Assertions.assertThat(reponse.getDocument().getContent()).isNotNull();
	}

}
