package fr.hackinghealth.web.rest.util;

import org.springframework.http.HttpHeaders;

/**
 * Utility class for http header creation.
 *
 */
public class HeaderUtil {

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-hackinghealthApp-alert", message);
        headers.add("X-hackinghealthApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("hackinghealthApp." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("hackinghealthApp." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("hackinghealthApp." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-hackinghealthApp-error", "error." + errorKey);
        headers.add("X-hackinghealthApp-params", entityName);
        return headers;
    }
}
