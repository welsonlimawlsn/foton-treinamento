package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContaPoupancaTest {

    private ContaPoupanca contaPoupanca;

    @Before
    public void setUp() {
        contaPoupanca = new ContaPoupanca();
        contaPoupanca.credita(200);
    }

    @Test
    public void deveDebitarValorNaContaComSaldo() {
        try {
            contaPoupanca.debita(199.99);
            assertEquals(0.01, contaPoupanca.getSaldo(), 0.001);
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

    @Test
    public void naoDeveDebitarValorNaContaComSaldoInsuficiente() {
        try {
            contaPoupanca.debita(200.01);
            fail();
        } catch (NegocioException e) {
            assertEquals(Mensagem.SALDO_INSUFICIENTE, e.getMensagem());
        }
    }

}
