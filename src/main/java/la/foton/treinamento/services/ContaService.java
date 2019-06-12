package la.foton.treinamento.services;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContaService {

    private ClienteService clienteService = ClienteService.getInstance();
    @Inject
    private ContaDAO contaDAO;

    public Conta abreConta(Cliente cliente, TipoDaConta tipo) throws NegocioException {
        cliente.ativa();
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

    public void sacaEmConta(Conta conta, double valor) throws NegocioException {
        conta.debita(valor);
        contaDAO.atualiza(conta);
    }

    public void depositaEmConta(Conta conta, double valor) {
        conta.credita(valor);
        contaDAO.atualiza(conta);
    }

    public void transfereEntreContas(Conta origem, Conta destino, double valor) throws NegocioException {
        origem.transfere(destino, valor);
        contaDAO.atualiza(origem);
        contaDAO.atualiza(destino);
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
