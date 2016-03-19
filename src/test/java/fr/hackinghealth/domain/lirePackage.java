package fr.hackinghealth.domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import fr.hackinghealth.domain.vidal.Response.ResponseSearch;
import fr.hackinghealth.domain.vidal.entry.PackageVidal;

/**
 * TODO décrire ici le rôle de la classe<br/>
 * créé le 19 mars 2016<br/>
 * @author bvenet<br/>
 */
public class lirePackage {
    
    public static JAXBContext context;
    
//    @BeforeClass
//    public static void preInit()
//            throws JAXBException {
//    }
    
    @Test
    public void lireXml() {
        try {
            context = JAXBContext.newInstance(ResponseSearch.class);
            JAXBElement<ResponseSearch> packObj =
                    context.createUnmarshaller().unmarshal(
                            new StreamSource(getClass().getResourceAsStream(/*"RespSearch.xml"*/"package.xml")),
                            ResponseSearch.class);
            ResponseSearch respPack = packObj.getValue();
            System.out.println(String.format("Title from XML : %s", respPack.getTitle()));
            Assert.assertNotNull(respPack.getPackages());
            Assertions.assertThat(respPack.getPackages()).isNotEmpty();
            PackageVidal pack = respPack.getPackages().get(0);
            System.out.println(String.format("Pack from XML : Id:%d - %s - %s", pack.getIdVidal(), pack.getName(),
                    pack.getCip13()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        
    }
    
}
