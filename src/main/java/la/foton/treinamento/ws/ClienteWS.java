package la.foton.treinamento.ws;

import la.foton.treinamento.services.ClienteService;
import la.foton.treinamento.util.NegocioException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteWS {

    @Inject
    private ClienteService clienteService;

    @POST
    @Path("/{cpf}/cadastra")
    public Response cadastraCliente(@PathParam("cpf") String cpf, @QueryParam("nome") String nome) throws NegocioException {
        clienteService.cadastraCliente(cpf, nome);
        return Response.created(UriBuilder.fromPath("cliente/{cpf}").build(cpf)).build();
    }

    @GET
    @Path("todos")
    public Response listaTodos() throws NegocioException {
        return Response.ok(clienteService.listaTodos()).build();
    }

    @GET
    @Path("{cpf}")
    public Response consultaPorCPF(@PathParam("cpf") String cpf) throws NegocioException {
        return Response.ok(clienteService.consultaPorCPF(cpf)).build();
    }

    @PUT
    @Path("{cpf}/ativa")
    public Response ativaCliente(@PathParam("cpf") String cpf) throws NegocioException {
        clienteService.ativa(cpf);
        return Response.ok().build();
    }

    @DELETE
    @Path("{cpf}/remove")
    public Response removeCliente(@PathParam("cpf") String cpf) throws NegocioException {
        clienteService.remove(cpf);
        return Response.noContent().build();
    }
}
