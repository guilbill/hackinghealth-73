package fr.hackinghealth.service.vidal;


@Produces({ MediaType.APPLICATION_ATOM_XML })
public interface SecurisationService {
	@POST
    @Path("/api/alerts")
    @Consumes({ "text/xml" })
    ResponseSecurisation securisation(final Prescription prescription);
    
}
