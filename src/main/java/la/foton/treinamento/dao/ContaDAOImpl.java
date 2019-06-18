package la.foton.treinamento.dao;

import la.foton.treinamento.entities.Conta;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Default
//@TransactionManagement(value = TransactionManagementType.CONTAINER)//opcional
public class ContaDAOImpl implements ContaDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer geraNumero() {
        TypedQuery<Integer> query = em.createQuery("select max(c.numero) from Conta c", Integer.class);

        Integer numero = query.getSingleResult();

        return numero != null ? numero + 1 : 1;
    }

    @Override
    public void insere(Conta conta) {
        em.persist(conta);
    }

    @Override
    public Conta consultaPorNumero(int numero) {
        return em.find(Conta.class, numero);
    }

    @Override
    public void atualiza(Conta conta) {
        em.merge(conta);
    }
}
