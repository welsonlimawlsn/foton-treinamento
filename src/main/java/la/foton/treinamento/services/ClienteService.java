package la.foton.treinamento.services;

import la.foton.treinamento.dao.ClienteDAO;
import la.foton.treinamento.entities.Cliente;
import la.foton.treinamento.entities.SituacaoDoCliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ClienteService {

    @Inject
    private ClienteDAO dao;

    public void validaSituacaoCliente(Cliente cliente) throws NegocioException {
        if (cliente.getSituacao() == SituacaoDoCliente.PENDENTE) {
            throw new NegocioException(Mensagem.CLIENTE_SITUACAO_PENDENTE);
        }
    }

    public Cliente consultaPorCPF(String cpf) throws NegocioException {
        Cliente cliente = dao.consultaPorCPF(cpf);
        if (cliente == null) {
            throw new NegocioException(Mensagem.CLIENTE_NAO_ENCONTRADO);
        }
        return cliente;
    }

    public List<Cliente> listaTodos() throws NegocioException {
        List<Cliente> clientes = dao.consultaTodos();
        if (clientes.isEmpty())
            throw new NegocioException(Mensagem.NAO_EXISTEM_CLIENTES);
        return clientes;
    }

    public void cadastraCliente(String cpf, String nome) throws NegocioException {
        if (cpf == null || cpf.isEmpty() || nome == null || nome.isEmpty()) {
            throw new NegocioException(Mensagem.CLIENTE_NAO_PODE_SER_CADASTRADO);
        }
        dao.insere(new Cliente(cpf, nome));
    }

    public void ativa(String cpf) throws NegocioException {
        Cliente cliente = consultaPorCPF(cpf);
        cliente.ativa();
        dao.atualiza(cliente);
    }

    public void remove(String cpf) throws NegocioException {
        Cliente cliente = consultaPorCPF(cpf);
        dao.delete(cliente);
    }
}
