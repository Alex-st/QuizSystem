package web;

import dao.domain.*;
import dao.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.QuestionsService;
import service.TopicsService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex on 9/18/15.
 */
@Controller("tutorController")
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    QuestionsService questionsService;

    @Autowired
    TopicsService topicsService;

    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(HttpServletRequest request, Model model) {


        Users curUser = (Users)request.getSession().getAttribute("user");
        List<Questions> curQuestions = questionsService.getQuestionsByAuthor(curUser);
        model.addAttribute("questions", curQuestions);

        return "tutorWelcomePage";
    }

    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/newQuestionForm", method = RequestMethod.GET)
    public String getQuestionForm(HttpServletRequest request, Model model) {

        String language = (String)request.getSession().getAttribute("locale");
        LangEnum curLang = LangEnum.ENG;

        if (language.equals("ru")) {
            curLang = LangEnum.RU;
        }

        List<Topics> topics = topicsService.getAllTopicsByLanguage(curLang);

        model.addAttribute("topics", topics);

        return "newQuestion";
    }

    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public String createNewQuestion(@RequestParam Map<String,String> allRequestParams, HttpServletRequest request, Model model) {
        Questions curQuestion = new Questions();

        String language = (String)request.getSession().getAttribute("locale");
        LangEnum curLang = LangEnum.ENG;

        if (language.equals("ru")) {
            curLang = LangEnum.RU;
        }

        Users curUser = (Users) request.getSession().getAttribute("user");

        curQuestion.setLanguage(curLang);
        curQuestion.setAuthor(curUser);
        curQuestion.setText(allRequestParams.get("qtext"));
        curQuestion.setTopic(topicsService.getTopicByName(allRequestParams.get("qtopic")));
        curQuestion.setIsMultipleQuestions(Boolean.valueOf(allRequestParams.get("isMultiChoice")));

        Set<Answers> answers = new HashSet<Answers>();

        if (allRequestParams.get("q1") != null) {
            Answers answer1 = new Answers();
            answer1.setText(allRequestParams.get("q1"));
            answer1.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer1")));
            answer1.setQuestion(curQuestion);
            answers.add(answer1);
        }

        if (allRequestParams.get("q2") != null) {
            Answers answer2 = new Answers();
            answer2.setText(allRequestParams.get("q2"));
            answer2.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer2")));
            answer2.setQuestion(curQuestion);
            answers.add(answer2);
        }

        if (allRequestParams.get("q3") != null) {
            Answers answer3 = new Answers();
            answer3.setText(allRequestParams.get("q3"));
            answer3.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer3")));
            answer3.setQuestion(curQuestion);
            answers.add(answer3);
        }

        if (allRequestParams.get("q4") != null) {
            Answers answer4 = new Answers();

            System.out.println(allRequestParams.get("q4"));

            answer4.setText(allRequestParams.get("q4"));
            answer4.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer4")));
            answer4.setQuestion(curQuestion);
            answers.add(answer4);
        }

        // -----------------validation-------------------
        if (answers.size() == 0) {
            if (request.getSession().getAttribute("locale").equals("ru"))
                model.addAttribute("resultMessage", "Введите хотя бы один ответ");
            else model.addAttribute("resultMessage", "Please input even one answer");
        }

        int checkingNumberOfCorrectAnswers = 0;

        for (Answers i: answers) {
            if (i.isCorrect())
                checkingNumberOfCorrectAnswers++;
        }

        if (checkingNumberOfCorrectAnswers == 0) {
            if (answers.size() == 0) {
                if (request.getSession().getAttribute("locale").equals("ru"))
                    model.addAttribute("resultMessage", "Введите хотя бы один правильный ответ");
                else model.addAttribute("resultMessage", "Please input even one сorrect answer");
            }
        }

        if (!curQuestion.isMultipleQuestions() && (checkingNumberOfCorrectAnswers > 1)) {
            if (request.getSession().getAttribute("locale").equals("ru"))
                model.addAttribute("resultMessage", "Количество правильных вопросов неверно");
            else model.addAttribute("resultMessage", "Number of correct answers doesn't match question's type");

            return "tutorWelcomePage";
        }

        curQuestion.setAnswers(answers);
        questionsService.addNewQuestion(curQuestion);

        if (request.getSession().getAttribute("locale").equals("ru"))
            model.addAttribute("resultMessage", "Вопрос успешно добавлен");
        else model.addAttribute("resultMessage", "Question was succesfully added");

        return "redirect:/tutor/";
    }

}
