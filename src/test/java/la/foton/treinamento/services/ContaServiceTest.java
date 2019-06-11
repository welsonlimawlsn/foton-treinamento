package la.foton.treinamento.services;

import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.NegocioException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ContaServiceTest {

    private Cliente titular;

    @Before
    public void setUp() {
        titular = new Cliente();
    }

    @Test
    public void deveAbrirUmaConta() {
        try {
            Conta conta = ContaService.getInstance().abreConta(titular, TipoDaConta.CORRENTE);

            assertEquals(500, ((ContaCorrente) conta).getLimiteChequeEspecial(), 0);
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

    @Test
    public void deveAbrirUmaContaPoupanca() {
        try {
            Conta conta = ContaService.getInstance().abreConta(titular, TipoDaConta.POUPANCA);

            assertEquals(1, ((ContaPoupanca) conta).getDiaAniversario());
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

}
