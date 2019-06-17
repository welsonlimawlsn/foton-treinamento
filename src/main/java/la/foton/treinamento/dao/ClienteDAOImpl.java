package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insere(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public Cliente consultaPorCPF(String cpf) {
        return em.find(Cliente.class, cpf);
    }

    @Override
    public List<Cliente> consultaTodos() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    public void atualiza(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        em.remove(cliente);
    }
}
