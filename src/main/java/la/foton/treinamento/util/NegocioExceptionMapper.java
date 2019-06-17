package la.foton.treinamento.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NegocioExceptionMapper implements ExceptionMapper<NegocioException> {
    @Override
    public Response toResponse(NegocioException e) {
        String mensagemUsuario = e.getMensagem() != null && e.getMensagem().getDescricao() != null
                ? e.getMensagem().getDescricao()
                : "Mensagem não identificada";
        String mensagemDesenvolvedor = e.getCause() != null && e.getCause().getMessage() != null
                ? e.getCause().getMessage()
                : "Mensagem não identificada";
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new Erro(mensagemUsuario, mensagemDesenvolvedor))
                .build();
    }
}
