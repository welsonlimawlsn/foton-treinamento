package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContaTest {

    private Conta conta;

    @Before
    public void setUp() {
        conta = new ContaCorrente();
        conta.credita(500);
    }

    @Test
    public void deveCreditarValorNaConta() {
        conta.credita(100);
        assertEquals(600, conta.getSaldo(), 0);
    }

    @Test
    public void deveDebitarValorNaContaQuePossuiSaldoSuficiente() {
        try {
            conta.debita(251);
            assertEquals(249, conta.getSaldo(), 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void naoDeveDebitarValorNaContaQuandoSaldoEhInsuficiente() {
        try {
            conta.debita(500.01);
            fail();
        } catch (NegocioException e) {
            assertEquals(Mensagem.SALDO_INSUFICIENTE, e.getMensagem());
            assertEquals(500, conta.getSaldo(), 0);
        }
    }

    @Test
    public void deveTranferirValorEntreContas() {
        Conta contaDeCredito = new ContaCorrente();
        try {
            conta.transfere(contaDeCredito, 499);
            assertEquals(1, conta.getSaldo(), 0);
            assertEquals(499, contaDeCredito.getSaldo(), 0);
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

    @Test
    public void deveEncerrarContaSemSaldo() {
        try {
            conta.debita(500);
            conta.encerra();
            assertEquals(EstadoDaConta.ENCERRADA, conta.getEstado());
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

    @Test
    public void naoDeveEncerrarContaJaEncerrada() {
        try {
            conta.debita(500);
            colocarAContaNoEstadoDeEncerrado();
            conta.encerra();
            fail();
        } catch (NegocioException e) {
            assertEquals(Mensagem.CONTA_JA_ENCERRADA, e.getMensagem());
        }
    }

    @Test
    public void naoDeveEncerrarContaQaundoContaAindaPossuirSaldo() {
        try {
            conta.encerra();
            fail();
        } catch (NegocioException e) {
            assertEquals(Mensagem.CONTA_POSSUI_SALDO, e.getMensagem());
        }
    }

    private void colocarAContaNoEstadoDeEncerrado() throws NegocioException {
        conta.encerra();
    }

}
