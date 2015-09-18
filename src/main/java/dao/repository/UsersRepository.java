package dao.repository;

import dao.domain.Questions;
import dao.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 8/17/15.
 */
@Repository("usersRepository")
public class UsersRepository implements UsersRepo {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<Users> getAllUsers(Users user) {
        TypedQuery<Users> query =
                em.createQuery("select q from Users q ", Users.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public int saveUser(Users user) {
        em.persist(user);
        em.flush();
        return user.getUserId();
    }

    @Override
    @Transactional
    public void updateUser(Users user) {
        int id = user.getUserId();
        Users temp = (Users) em.find(Users.class, id);
        temp.setEmail(user.getEmail());
        temp.setName(user.getName());
        temp.setLogin(user.getLogin());
        temp.setSurname(user.getSurname());
    }

    @Override
    @Transactional
    public void deleteUser(Users user) {
        Users temp = em.find(Users.class, user.getUserId());
        em.remove(temp);
    }

    @Override
    public Users getUserById(int id) {
        return em.find(Users.class, id);
    }

    @Override
    public Users getUserByLogin(String login) {
        TypedQuery<Users> query =
                em.createQuery("select u from Users u where u.login = :log", Users.class);

        Users temp = null;
        try {
            temp = query.setParameter("log", login).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            temp = null;
            e.printStackTrace();
        }

        return temp;
    }

    @Override
    public List<Users> getAllUsers() {
        TypedQuery<Users> query =
                em.createQuery("select u from Users u", Users.class);
        return query.getResultList();
    }

}
