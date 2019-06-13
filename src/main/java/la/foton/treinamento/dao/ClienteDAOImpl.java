package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
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
}
