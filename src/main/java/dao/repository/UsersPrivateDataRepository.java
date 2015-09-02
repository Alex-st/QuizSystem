package dao.repository;

import dao.domain.Questions;
import dao.domain.Users;
import dao.domain.UsersPrivateData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
public class UsersPrivateDataRepository implements UsersPrivateDataRepo {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    @Transactional
    public int saveUserCredentials(UsersPrivateData credentials) {
        em.persist(credentials);
        em.flush();
        return credentials.getUsersDataId();
    }

    @Override
    @Transactional
    public void deleteCredentials(UsersPrivateData credentials) {
        UsersPrivateData temp = em.find(UsersPrivateData.class, credentials.getUsersDataId());
        em.remove(temp);
    }

    @Override
    public List<Users> getAllStudents() {
        TypedQuery<Users> query =
                em.createQuery("select u.user from UsersPrivateData u where u.role = RoleEnum.STUDENT", Users.class);

        return query.getResultList();
    }

    @Override
    public List<Users> getAllTutors() {
        TypedQuery<Users> query =
                em.createQuery("select u.user from UsersPrivateData u where u.role = RoleEnum.TUTOR", Users.class);

        return query.getResultList();
    }
}
