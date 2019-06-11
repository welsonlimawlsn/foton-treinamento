package la.foton.treinamento.services;

import la.foton.treinamento.entities.*;
import la.foton.treinamento.util.NegocioException;

public class ContaService {

    private static ContaService instance;

    private ClienteService clienteService = ClienteService.getInstance();

    private ContaService() {
    }

    public static ContaService getInstance() {
        if (instance == null) {
            instance = new ContaService();
        }
        return instance;
    }

    public Conta abreConta(Cliente cliente, TipoDaConta tipo) throws NegocioException {
        cliente.ativa();
        clienteService.validaSituacaoCliente(cliente);
        Conta conta = criarConta(tipo);
        conta.setTitular(cliente);


        conta.setAgencia(1234);

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
