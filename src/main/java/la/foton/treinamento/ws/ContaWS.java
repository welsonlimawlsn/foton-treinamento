package la.foton.treinamento.ws;

import la.foton.treinamento.dto.TransferenciaDTO;
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
    public Response consulta(@PathParam("numero") int numero) throws NegocioException {
        return Response.ok(contaService.consultaPorNumero(numero)).build();
    }

    @PUT
    @Path("{numero}/deposita")
    public Response deposita(@PathParam("numero") int numero, @QueryParam("valor") double valor) throws NegocioException {
        contaService.depositaEmConta(numero, valor);
        return Response.ok().build();
    }


    @PUT
    @Path("{numero}/saca")
    public Response saca(@PathParam("numero") int numero, @QueryParam("valor") double valor) throws NegocioException {
        contaService.sacaEmConta(numero, valor);
        return Response.ok().build();
    }


    @PUT
    @Path("transfere")
    public Response saca(TransferenciaDTO transferencia) throws NegocioException {
        contaService.transfereEntreContas(transferencia);
        return Response.ok().build();
    }


}
