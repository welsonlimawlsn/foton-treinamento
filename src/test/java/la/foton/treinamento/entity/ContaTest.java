package la.foton.treinamento.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContaTest {

    private Conta conta;

    @Before
    public void setUp() {
        conta = new Conta();
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
        } catch (Exception e) {
            assertEquals("Saldo insuficiente", e.getMessage());
            assertEquals(500, conta.getSaldo(), 0);
        }
    }

    @Test
    public void deveTranferirValorEntreContas() {
        Conta contaDeCredito = new Conta();
        try {
            conta.transfere(contaDeCredito, 499);
            assertEquals(1, conta.getSaldo(), 0);
            assertEquals(499, contaDeCredito.getSaldo(), 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deveEncerrarContaSemSaldo() {
        try {
            conta.encerra();
            assertEquals(EstadoDaConta.ENCERRADA, conta.getEstado());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void naoDeveEncerrarContaJaEncerrada() {
        try {
            colocarAContaNoEstadoDeEncerrado();
            conta.encerra();
            fail();
        } catch (Exception e) {
            assertEquals("Conta já encerrada", e.getMessage());
        }
    }

    private void colocarAContaNoEstadoDeEncerrado() throws Exception {
        conta.encerra();
    }

}
