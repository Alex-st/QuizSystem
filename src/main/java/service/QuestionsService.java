package service;

import dao.domain.*;

import java.util.List;
import java.util.Set;

/**
 * Created by alex on 9/7/15.
 */
public interface QuestionsService {
    int addNewQuestion(Questions question);

    void removeQuestion(Questions question);

    void updateQuestion(Questions newQuestion);

    Questions getQuestionById(int id);

    List<Questions> getQuestionsByAuthor(Users tutor);

    List<Questions> getAllQuestionsByTopic(Topics topic);

    List<Questions> getAllQuestionsByTopicAndLanguage(Topics topic, LangEnum lang);

    Set<Answers> getAllQuestionAnswers(Questions question);

    double countMarkForReceivedQuestionAnswers(Questions question, List<Answers> receivedAnswers);
}
