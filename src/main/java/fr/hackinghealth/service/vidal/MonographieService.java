package fr.hackinghealth.service.vidal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import fr.hackinghealth.domain.vidal.MonographieReponse;

public interface MonographieService {
	@Path("/api/package/{idVidal}/documents/opt")
	@GET
	MonographieReponse documentsOptimaux(@PathParam("idVidal") final int idVidal);
}
