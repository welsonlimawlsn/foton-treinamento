package la.foton.treinamento.services;

import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClienteServiceTest {

    private Cliente cliente;

    @Before
    public void setUp() {
        cliente = new Cliente();
    }

    @Test
    public void naoDeveValidaClienteSituacaoPendente() {
//        try {
//            ClienteService.getInstance().validaSituacaoCliente(cliente);
//            fail();
//        } catch (NegocioException e) {
//            assertEquals(Mensagem.CLIENTE_SITUACAO_PENDENTE, e.getMensagem());
//        }
    }

}
