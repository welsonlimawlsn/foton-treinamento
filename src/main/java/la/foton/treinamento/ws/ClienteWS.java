package la.foton.treinamento.ws;

import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.services.ClienteService;
import la.foton.treinamento.util.NegocioException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteWS {

    @Inject
    private ClienteService clienteService;

    @POST
    @Path("/{cpf}/cadastra")
    public Response cadastraCliente(@PathParam("cpf") String cpf, @QueryParam("nome") String nome) {
        try {
            clienteService.cadastraCliente(cpf, nome);
        } catch (NegocioException e) {
            throw new WebApplicationException(Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMensagem().getDescricao())
                    .build());
        }
        return Response.created(UriBuilder.fromPath("cliente/{cpf}").build(cpf)).build();
    }

    @GET
    public Response listaTodos() {
        List<Cliente> clientes;
        try {
            clientes = clienteService.listaTodos();
        } catch (NegocioException e) {
            throw new WebApplicationException(Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMensagem().getDescricao())
                    .build());
        }
        return Response.ok(clientes).build();
    }
}
