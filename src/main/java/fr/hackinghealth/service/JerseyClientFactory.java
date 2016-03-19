package fr.hackinghealth.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

public class JerseyClientFactory {
    
    private final String baseUrl;
    
    private Client jerseyClient;
    
    private String nomUtilisateur;
    private String motDePasse;
    
    
    public JerseyClientFactory(final String baseUrl, final String nomUtilisateur, final String motDePasse,
            final int timeout) {
        this.baseUrl = baseUrl;
        initClient(nomUtilisateur, motDePasse, timeout);
    }
    
    private void initClient(final String nomUtilisateur, final String motDePasse, final int timeout) {
        jerseyClient = ClientBuilder.newClient();
        jerseyClient.property(ClientProperties.CONNECT_TIMEOUT, timeout);
        jerseyClient.property(ClientProperties.READ_TIMEOUT, timeout);
       

    }
    
    public <T> T getClient(final Class<T> clazz) {
        final WebTarget target = jerseyClient.target(baseUrl);
       
        return WebResourceFactory.newResource(clazz, target);
    }
}