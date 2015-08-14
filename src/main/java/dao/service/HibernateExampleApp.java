package dao.service;

import dao.domain.RoleEnum;
import dao.domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by alex on 7/30/15.
 */
public class HibernateExampleApp {

    public static void main(String[] args) {

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

        em.close();
        emf.close();

    }

}
