package la.foton.treinamento.ws;

import la.foton.treinamento.services.ContaService;
import la.foton.treinamento.util.NegocioException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaWS {

    @Inject
    private ContaService contaService;

    @GET
    @Path("hello")
    public Response hello() {
        return Response.ok().entity("API Rest Conta").build();
    }

    @GET
    @Path("{numero}")
    public Response consulta(@PathParam("numero") int numero) {
        try {
            return Response.ok(contaService.consultaPorNumero(numero)).build();
        } catch (NegocioException e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(e.getMensagem().getDescricao()).build());
        }
    }
}
