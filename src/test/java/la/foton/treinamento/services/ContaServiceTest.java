package la.foton.treinamento.services;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.NegocioException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaServiceTest {

    private Cliente titular;
    private ContaDAO contaDAO;
    private ContaService contaService;

    @Before
    public void setUp() {
        titular = new Cliente();
    }

    @Test
    public void deveAbrirUmaConta() {
        try {
            Conta conta = contaService.abreConta(titular.getCpf(), TipoDaConta.CORRENTE);
            Conta contaConsultada = contaDAO.consultaPorNumero(conta.getNumero());
            assertNotNull(contaConsultada);
            assertEquals(conta.getSaldo(), contaConsultada.getSaldo(), 0);
            assertEquals(500, ((ContaCorrente) conta).getLimiteChequeEspecial(), 0);
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

    @Test
    public void deveAbrirUmaContaPoupanca() {
        try {
            Conta conta = contaService.abreConta(titular.getCpf(), TipoDaConta.POUPANCA);

            assertEquals(1, ((ContaPoupanca) conta).getDiaAniversario());
        } catch (NegocioException e) {
            fail(e.getMensagem().getDescricao());
        }
    }

}
