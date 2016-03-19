package fr.hackinghealth.service.vidal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.hackinghealth.domain.vidal.securisation.ReponseSecurisation;
import fr.hackinghealth.domain.vidal.securisation.input.Prescription;


@Produces({ MediaType.APPLICATION_ATOM_XML })
public interface SecurisationService {
	@POST
    @Path("/api/alerts")
	@Consumes({ "text/xml" })
    ReponseSecurisation securisation(@QueryParam("app_id") String app_id, @QueryParam("app_key") String app_key,final Prescription prescription);

}
