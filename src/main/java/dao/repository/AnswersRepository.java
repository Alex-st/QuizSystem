package dao.repository;

import dao.domain.Answers;
import dao.domain.Questions;
import dao.domain.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by alex on 8/17/15.
 */
@Repository("answersRepository")
public class AnswersRepository implements AnswersRepo {

    @PersistenceContext(name = "HibernateMySQL")
    private EntityManager em;

    @Override
    @Transactional
    public int saveAnswer(Answers answer) {
        em.persist(answer);
        em.flush();
        return answer.getAnswerId();
    }

    @Override
    @Transactional
    public void updateAnswer(Answers answer) {
        int id = answer.getAnswerId();
        Answers temp = (Answers) em.find(Answers.class, id);
        temp.setQuestion(answer.getQuestion());
        temp.setText(answer.getText());
        temp.setIsCorrect(answer.isCorrect());
    }

    @Override
    @Transactional
    public void deleteAnswer(Answers answer) {
        Answers temp = em.find(Answers.class, answer.getAnswerId());
        em.remove(temp);
    }


}
