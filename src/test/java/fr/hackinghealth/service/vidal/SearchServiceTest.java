package fr.hackinghealth.service.vidal;

import javax.xml.bind.JAXBException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import fr.hackinghealth.domain.vidal.Response.ResponseSearch;
import fr.hackinghealth.domain.vidal.entry.PackageVidal;
import fr.hackinghealth.service.JerseyClientFactory;

public class SearchServiceTest {
    
    @Test
    public void testSearch()
            throws JAXBException {
        
        JerseyClientFactory factory =
                new JerseyClientFactory("http://apirest-dev.vidal.fr/rest/", "9fd557d3",
                        "06cbdb4357601927c22dc87d4ea469bc", 1000);
        SearchService fixture = factory.getClient(SearchService.class);
        
        String code = "3232018";
        ResponseSearch reponse = fixture.search("9fd557d3", "06cbdb4357601927c22dc87d4ea469bc", code, SearchService.FILTER_PACKAGE);
        
        Assertions.assertThat(reponse).isNotNull();
        PackageVidal pack = reponse.getPackages().get(0);
        System.out.println(String.format("Pack from XML : Id:%s - %s - %s", pack.getCip13(), pack.getName(),
                pack.getIdVidal()));
    }
    
}
