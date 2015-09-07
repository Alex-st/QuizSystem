package dao.repository;

import dao.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 8/17/15.
 */
@Repository("questionsRepository")
public class QuestionsRepository implements QuestionsRepo {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    public List<Questions> getAllQuestionsOfTutor(Users user) {
        TypedQuery<Questions> query =
                em.createQuery("select q from Questions q where q.author = :u", Questions.class);

        return query.setParameter("u", user).getResultList();
    }

    @Override
    @Transactional
    public int saveQuestion(Questions question) {
        em.persist(question);
        em.flush();
        return question.getQuestionId();
    }

    @Override
    @Transactional
    public void updateQuestion(Questions question) {
        int id = question.getQuestionId();
        Questions temp = (Questions) em.find(Questions.class, id);
        temp.setAuthor(question.getAuthor());
        temp.setAnswers(question.getAnswers());
        temp.setIsMultipleQuestions(question.isMultipleQuestions());
        temp.setLanguage(question.getLanguage());
        temp.setText(question.getText());
        temp.setTopic(question.getTopic());
    }

    @Override
    public Questions getQuestionById(int id) {
        Questions temp = (Questions) em.find(Questions.class, id);
        return temp;
    }

    @Override
    @Transactional
    public void deleteQuestion(Questions questions) {
        Questions temp = em.find(Questions.class, questions.getQuestionId());
        em.remove(temp);
    }

    @Override
    public Set<Answers> getAllQuestionAnswers(Questions question) {
        return question.getAnswers();
    }

    @Override
    public List<Questions> getAllQuestionsbyTopic(Topics topic) {
        TypedQuery<Questions> query =
                em.createQuery("select q from Questions q where q.topic = :tc", Questions.class);

        return query.setParameter("tc", topic).getResultList();
    }

    @Override
    public List<Questions> getAllQuestionsbyTopicAndLanguage(Topics topic, LangEnum lang) {
        TypedQuery<Questions> query =
                em.createQuery("select q from Questions q where q.topic = :tc and q.language = :lg", Questions.class);

        query.setParameter("tc", topic);
        query.setParameter("lg", lang);
        return query.getResultList();
    }

}
