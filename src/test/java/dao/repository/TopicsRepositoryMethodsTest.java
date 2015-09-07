package dao.repository;

import dao.domain.LangEnum;
import dao.domain.Topics;
import service.AppEMFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 8/17/15.
 */
// Test was actual before adding Spring to project
public class TopicsRepositoryMethodsTest {

    private TopicsRepo topicsRepo;
    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void init() {
        emf = AppEMFactory.getAppEMFactory();

        em = emf.createEntityManager();
    }

    @Test
    @Ignore
    public void checkInsertAndDeleteToDB() {

        TopicsRepo topicsRepo = new TopicsRepository(em);

        System.out.println("Insert new Topic to DB / Start --------------");
        Topics myTestTopic = new Topics();
        myTestTopic.setTopicName("Test");
        myTestTopic.setTopicDesc("This is Test topic");
        myTestTopic.setTopicLanguage(LangEnum.ENG);


        int topicId = topicsRepo.saveTopic(myTestTopic);

        System.out.println(myTestTopic);
        System.out.println("Insert new topic to DB / Finished ------------");

        System.out.println("Delete topic from DB / Start ------------");
        Topics mySecondTestTopic = em.find(Topics.class, topicId);

        assertEquals("Created and inserted topics are not equal", myTestTopic, mySecondTestTopic);

        topicsRepo.deleteTopic(mySecondTestTopic);

        System.out.println("Delete finished --------------------");


    }

    @After
    public void afterClass() {

        em.close();
        emf.close();
    }
}
