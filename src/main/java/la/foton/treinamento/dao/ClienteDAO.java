package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    void insere(Cliente cliente);

    Cliente consultaPorCPF(String cpf);

    List<Cliente> consultaTodos();
}
