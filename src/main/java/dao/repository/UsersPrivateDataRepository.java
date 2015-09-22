package dao.repository;

import dao.domain.Questions;
import dao.domain.Users;
import dao.domain.UsersPrivateData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
@Repository("usersPrivateDataRepository")
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

    //We update only password and login
    @Override
    @Transactional
    public void updateUserCredentials(UsersPrivateData credentials) {
        UsersPrivateData temp = em.find(UsersPrivateData.class, credentials.getUsersDataId());
        temp.setLogin(credentials.getLogin());
        temp.setPass(credentials.getPass());
        em.merge(temp);
    }

    @Override
    @Transactional
    public void deleteCredentials(UsersPrivateData credentials) {
        UsersPrivateData temp = em.find(UsersPrivateData.class, credentials.getUsersDataId());
        em.remove(temp);
    }

    @Override
    public UsersPrivateData getCredentialsByUser(Users user) {
        TypedQuery<UsersPrivateData> query = em.createQuery("select cr from UsersPrivateData cr where cr.user = :param", UsersPrivateData.class);
        query.setParameter("param", user);
        return query.getSingleResult();
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
