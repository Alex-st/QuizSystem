package dao.domain;

import dao.service.AppEMFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by alex on 8/14/15.
 */
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JPADomainEntitiesTest {

    @Test
 //   @Ignore
    public void testSomeMethod() {

        EntityManagerFactory emf = AppEMFactory.getAppEMFactory();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateExamleUnit");

        System.out.println(emf==null);

        EntityManager em = emf.createEntityManager();

        System.out.println(em==null);

        Users temp = new Users();
        temp.setName("AlexTest");
        temp.setEmail("alextest@i.ua");
        temp.setLogin("alextest");
        temp.setPass("test");
        temp.setRole(RoleEnum.TUTOR);

        try {
            em.getTransaction().begin();
            em.persist(temp);

            temp = em.find(Users.class, 1);
            System.out.println(temp);

            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        System.out.println(temp.toString());

        Users resultOfTest = em.find(Users.class, 1);

        em.close();
        emf.close();

        assertNotNull(resultOfTest);
    }
}
