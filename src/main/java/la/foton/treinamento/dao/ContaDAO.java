package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Conta;

public interface ContaDAO {

    Integer geraNumero();

    void insere(Conta conta);

    Conta consultaPorNumero(int numero);

    void atualiza(Conta conta);
}
