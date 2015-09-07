package dao.repository;

import dao.domain.Results;
import dao.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
@Repository("resultsRepository")
public class ResultsRepository implements ResultsRepo {
    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    @Transactional
    public int saveUserResult(Results res) {
        em.persist(res);
        em.flush();
        return res.getResultId();
    }

    @Override
    @Transactional
    public void deleteUserResult(Results res) {
        Results temp = em.find(Results.class, res.getResultId());
        em.remove(temp);
    }

    @Override
    public List<Results> getAllResults() {
        TypedQuery<Results> query =
                em.createQuery("select r from Results r ", Results.class);
        return query.getResultList();
    }

    @Override
    public List<Results> getAllStudentResults(Users user) {
        TypedQuery<Results> query =
                em.createQuery("select r from Results r where r.student = :st", Results.class);

        return query.setParameter("st", user).getResultList();
    }

}
