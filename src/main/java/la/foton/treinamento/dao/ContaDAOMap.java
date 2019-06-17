package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.entities.Conta;
import la.foton.treinamento.entities.ContaCorrente;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Alternative;
import java.util.HashMap;
import java.util.Map;

@Alternative
@Singleton
@Startup
public class ContaDAOMap implements ContaDAO {

    private Map<Integer, Conta> contas;

    @PostConstruct
    private void init() {
        contas = new HashMap<>();
        ContaCorrente contaCorrente = criaContaTeste();
        contas.put(1, contaCorrente);
    }

    private ContaCorrente criaContaTeste() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia(1234);
        contaCorrente.setAgencia(1);
        contaCorrente.setTitular(new Cliente("123.456.789-12", "Welson de Lima Teles"));
        contaCorrente.credita(299784325.65);
        return contaCorrente;
    }

    @Override
    public Integer geraNumero() {
        return contas.size() + 1;
    }

    @Override
    public void insere(Conta conta) {
        contas.put(geraNumero(), conta);
    }

    @Override
    public Conta consultaPorNumero(int numero) {
        if (contas.containsKey(numero))
            return contas.get(numero);
        return null;
    }

    @Override
    public void atualiza(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }
}
