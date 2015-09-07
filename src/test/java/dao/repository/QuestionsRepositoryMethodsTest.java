package dao.repository;

import dao.domain.Answers;
import dao.domain.LangEnum;
import dao.domain.Questions;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.AppEMFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

/**
 * Created by alex on 8/17/15.
 */
public class QuestionsRepositoryMethodsTest {

    @Autowired
    private QuestionsRepo questionsRepository;

    @Autowired
    private AnswersRepo answersRepository;

    @Autowired
    private TopicsRepo topicsRepository;

    @Test
    @Ignore
    public void testCreatingQuestion() {

        System.out.println(topicsRepository.getAllTopics().get(0));

//        Questions question = new Questions();
//        question.setText("What does term \'Integration\' mean?");
//        question.setLanguage(LangEnum.ENG);
//        question.setIsMultipleQuestions(false);
//
//        question.setTopic(topicsRepository.getAllTopics().get(0));
//        //question.setAuthor();
//
//        Answers answer1 = new Answers();
//        answer1.setText("Operation inverted to differentiation");
//        answer1.setIsCorrect(true);
//        answer1.setQuestion(question);
//
//        Answers answer2 = new Answers();
//        answer2.setText("Derivative calculation process");
//        answer2.setIsCorrect(false);
//        answer2.setQuestion(question);
//
//        Answers answer3 = new Answers();
//        answer3.setText("Derivative calculation process");
//        answer3.setIsCorrect(false);
//        answer3.setQuestion(question);
//
//        Set<Answers> answers = new HashSet<>();
//        answers.add(answer1);
//        answers.add(answer2);
//        answers.add(answer3);
//
//        question.setAnswers(answers);

//        int id = questionsRepository.saveQuestion(question);
//        System.out.println(id);
//
//        assertNotNull(id);
    }
}
