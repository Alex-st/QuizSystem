package dao.repository;

import dao.domain.LangEnum;
import dao.domain.Topics;
import org.springframework.stereotype.Repository;
import service.AppEMFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 8/17/15.
 */

@Repository("topicsRepository")
public class TopicsRepository implements TopicsRepo {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    public TopicsRepository() {
    }

    public TopicsRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Topics> getAllTopics() {
        TypedQuery<Topics> query =
                em.createQuery("select t from Topics t ", Topics.class);
        return query.getResultList();
    }

    @Override
    public List<Topics> getLanguageTopics(LangEnum lang) {
        TypedQuery<Topics> query =
                em.createQuery("select t from Topics t where t.topicLanguage = :lg", Topics.class);

        return query.setParameter("lg", lang).getResultList();
    }

    @Override
    @Transactional
    public int saveTopic(Topics topic) {
    //    em.getTransaction().begin();
        em.persist(topic);
        em.flush();
    //    em.getTransaction().commit();
        return topic.getTopicId();
    }

    @Override
    @Transactional
    public void deleteTopic(Topics topic) {

    //    em.getTransaction().begin();
        em.remove(topic);
    //    em.getTransaction().commit();
    }

    @Override
    public Topics getTopicById(int id) {
        return em.find(Topics.class, id);
    }

    @Override
    public Topics getTopicByName(String topicName) {
        TypedQuery<Topics> query =
                em.createQuery("select t from Topics t where t.topicName = :name", Topics.class);

        return query.setParameter("name", topicName).getSingleResult();
    }
    //for test purposes
    public static void main(String[] args) {

        EntityManagerFactory emf = AppEMFactory.getAppEMFactory();
        EntityManager em = emf.createEntityManager();

        TopicsRepo topicsRepo = new TopicsRepository(em);

        Topics myTestTopic = new Topics();

        myTestTopic.setTopicName("Test");
        myTestTopic.setTopicDesc("This is Test topic");
        myTestTopic.setTopicLanguage(LangEnum.ENG);

        System.out.println(myTestTopic);

        int topicId = topicsRepo.saveTopic(myTestTopic);

        Topics mySecondTestTopic = em.find(Topics.class, topicId);
        mySecondTestTopic.toString();

        System.out.println("-------------test for saving topic-----------------------");
        System.out.println("Saved topic"+mySecondTestTopic);
//        System.out.println(mySecondTestTopic.equals(myTestTopic));
        System.out.println("---------test for getting topic by lang---------------------------");

        for (Topics i : topicsRepo.getLanguageTopics(LangEnum.ENG)) {
            System.out.println(i.getTopicName()+"/"+i.getTopicDesc()+"/"+i.getTopicLanguage());
        }

        System.out.println("----------------test for deleting----------------------");
        topicsRepo.deleteTopic(mySecondTestTopic);
        System.out.println("Delete finished --------------------");

        System.out.println(myTestTopic);

        em.close();
        emf.close();


    }

}
