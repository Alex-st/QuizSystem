package service;

import dao.domain.*;
import dao.repository.AnswersRepo;
import dao.repository.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by alex on 9/7/15.
 */
@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepo questionsRepository;

//    @Autowired
//    private AnswersRepo answersRepository;

    @Override
    public int addNewQuestion(Questions question) {
        return questionsRepository.saveQuestion(question);
    }

    @Override
    public void removeQuestion(Questions question) {
        questionsRepository.deleteQuestion(question);
    }

    @Override
    public void updateQuestion(Questions newQuestion) {
        questionsRepository.updateQuestion(newQuestion);
    }

    @Override
    public Questions getQuestionById(int id) {
        return questionsRepository.getQuestionById(id);
    }

    @Override
    public List<Questions> getQuestionsByAuthor(Users tutor) {
        return questionsRepository.getAllQuestionsOfTutor(tutor);
    }

    @Override
    public List<Questions> getAllQuestionsByTopic(Topics topic){
        return questionsRepository.getAllQuestionsbyTopic(topic);
    }

    @Override
    public List<Questions> getAllQuestionsByTopicAndLanguage(Topics topic, LangEnum lang) {
        return questionsRepository.getAllQuestionsbyTopicAndLanguage(topic, lang);
    }

    @Override
    public Set<Answers> getAllQuestionAnswers(Questions question) {
        return questionsRepository.getAllQuestionAnswers(question);
    }

    @Override
    public double countMarkForReceivedQuestionAnswers(Questions question, List<Answers> receivedAnswers){
        double mark = 0.;
        if (!question.isMultipleQuestions()) {
            if (receivedAnswers.get(0).isCorrect()) {
                return 1.;
            }
            else return 0.;
        }

        List<Answers> correctAnswers = questionsRepository.getAllCorrectAnswersOfQuestion(question);

        for (Answers i: receivedAnswers) {
            if (correctAnswers.contains(i)) {
                mark += 1/correctAnswers.size();
            }
            else mark-= 1/correctAnswers.size();
        }


        if (mark < 0)
            return 0.;

        return mark;
    }

}
