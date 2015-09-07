package dao.repository;

import dao.domain.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 9/2/15.
 */
public interface QuestionsRepo {
    List<Questions> getAllQuestionsOfTutor(Users user);

    @Transactional
    int saveQuestion(Questions question);

    @Transactional
    void updateQuestion(Questions question);

    @Transactional
    void deleteQuestion(Questions questions);

    public Questions getQuestionById(int id);

    Set<Answers> getAllQuestionAnswers(Questions question);

    List<Questions> getAllQuestionsbyTopic(Topics topic);

    List<Questions> getAllQuestionsbyTopicAndLanguage(Topics topic, LangEnum lang);
}
