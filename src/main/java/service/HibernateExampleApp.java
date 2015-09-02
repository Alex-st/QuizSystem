package service;

import dao.domain.LangEnum;
import dao.domain.Topics;
import dao.repository.TopicsRepo;
import dao.repository.TopicsRepository;

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

//        Users temp = new Users();
//        temp.setName("AlexTest");
//        temp.setEmail("alextest@i.ua");
//        temp.setLogin("alextest");
//        temp.setPass("test");
//        temp.setRole(RoleEnum.TUTOR);
//
//        try {
//            em.getTransaction().begin();
//            em.persist(temp);
//
//            temp = em.find(Users.class, 1);
//            System.out.println(temp);
//
//            em.getTransaction().commit();
//        } finally {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//        }
//
//        System.out.println(temp.toString());
//
//        em.close();
//        emf.close();




        TopicsRepo topicsRepo = new TopicsRepository(em);
        Topics myTestTopic = new Topics();
        myTestTopic.setTopicName("Test");
        myTestTopic.setTopicDesc("This is Test topic");
        myTestTopic.setTopicLanguage(LangEnum.ENG);
        System.out.println(myTestTopic);

        int topicId = topicsRepo.saveTopic(myTestTopic);

        Topics mySecondTestTopic = em.find(Topics.class, topicId);
        mySecondTestTopic.toString();

        System.out.println("------------------------------------");
        System.out.println(mySecondTestTopic);
//        System.out.println(mySecondTestTopic.equals(myTestTopic));
        System.out.println("------------------------------------");
        topicsRepo.deleteTopic(mySecondTestTopic);
        System.out.println("Delete finished --------------------");
        System.out.println(myTestTopic);

        em.close();
        emf.close();
    }

}
