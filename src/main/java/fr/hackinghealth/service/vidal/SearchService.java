package fr.hackinghealth.service.vidal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.hackinghealth.domain.vidal.Response.ResponseSearch;

@Produces({ MediaType.APPLICATION_ATOM_XML })
public interface SearchService {
    /**
     * 
     */
    public static final String FILTER_PACKAGE = "PACKAGE";

    @GET
    @Path("/api/search")
    ResponseSearch search(@QueryParam("app_id") final String app_id, @QueryParam("app_key") final String app_key,
            @QueryParam("code") final String code, @QueryParam("filter") final String... filter);
    
}
