package la.foton.treinamento.services;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.dto.TransferenciaDTO;
import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContaService {

    @EJB
    private ClienteService clienteService;
    @Inject
    private ContaDAO contaDAO;

    public Conta abreConta(String cpfCliente, TipoDaConta tipo) throws NegocioException {
        Cliente cliente = clienteService.consultaPorCPF(cpfCliente);
        clienteService.validaSituacaoCliente(cliente);
        Conta conta = criarConta(tipo);
        conta.setTitular(cliente);

        conta.setNumero(contaDAO.geraNumero());
        conta.setAgencia(1234);

        contaDAO.insere(conta);

        return conta;
    }

    public void encerraConta(Conta conta) throws NegocioException {
        conta.encerra();
        contaDAO.atualiza(conta);
    }

    public void sacaEmConta(int numero, double valor) throws NegocioException {
        Conta conta = consultaPorNumero(numero);
        conta.debita(valor);
        contaDAO.atualiza(conta);
    }

    public void depositaEmConta(int numero, double valor) throws NegocioException {
        Conta conta = consultaPorNumero(numero);
        conta.credita(valor);
        contaDAO.atualiza(conta);
    }

    public void transfereEntreContas(TransferenciaDTO transferencia) throws NegocioException {
        Conta contaDebito = consultaPorNumero(transferencia.getNumeroContaDebito());
        Conta contaCredito = consultaPorNumero(transferencia.getNumeroContaCredito());
        contaDebito.transfere(contaCredito, transferencia.getValor());
        contaDAO.atualiza(contaDebito);
        contaDAO.atualiza(contaCredito);
    }

    public Conta consultaPorNumero(int numero) throws NegocioException {
        Conta conta = contaDAO.consultaPorNumero(numero);
        if (conta == null)
            throw new NegocioException(Mensagem.CONTA_NAO_ENCONTRADA);
        return conta;
    }

    private Conta criarConta(TipoDaConta tipo) {
        Conta conta = null;

        if (tipo == TipoDaConta.CORRENTE) {
            conta = new ContaCorrente();
            ((ContaCorrente) conta).setLimiteChequeEspecial(500);
        } else if (tipo == TipoDaConta.POUPANCA) {
            conta = new ContaPoupanca();
            ((ContaPoupanca) conta).setDiaAniversario(1);
        }

        return conta;
    }
}
