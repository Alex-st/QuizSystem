package web;

import dao.domain.*;
import dao.repository.TopicsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
@Secured("ROLE_TUTOR")
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    QuestionsService questionsService;

    @Autowired
    TopicsService topicsService;

    private static final Logger logger =
            LoggerFactory.getLogger(StudentsController.class);

//    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(HttpServletRequest request, Model model) {


        Users curUser = (Users)request.getSession().getAttribute("user");
        List<Questions> curQuestions = questionsService.getQuestionsByAuthor(curUser);
        model.addAttribute("questions", curQuestions);

        return "tutorWelcomePage";
    }

//    @Secured("ROLE_TUTOR")
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

//    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public String createNewQuestion(@RequestParam Map<String,String> allRequestParams,
                                    HttpServletRequest request, Model model,
                                    RedirectAttributes redirectAttributes) {
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

        System.out.println(curQuestion.getText());
        redirectAttributes.addFlashAttribute("curQuestion", curQuestion);

        if (allRequestParams.get("q1") != null) {
            Answers answer1 = new Answers();
            answer1.setText(allRequestParams.get("q1"));
            answer1.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer1")));
            answer1.setQuestion(curQuestion);
            answers.add(answer1);

            redirectAttributes.addFlashAttribute("q1", answer1);
        }

        if (allRequestParams.get("q2") != null) {
            Answers answer2 = new Answers();
            answer2.setText(allRequestParams.get("q2"));
            answer2.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer2")));
            answer2.setQuestion(curQuestion);
            answers.add(answer2);

            redirectAttributes.addFlashAttribute("q2", answer2);
        }

        if (allRequestParams.get("q3") != null) {
            Answers answer3 = new Answers();
            answer3.setText(allRequestParams.get("q3"));
            answer3.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer3")));
            answer3.setQuestion(curQuestion);
            answers.add(answer3);

            redirectAttributes.addFlashAttribute("q3", answer3);
        }

        if (allRequestParams.get("q4") != null) {
            Answers answer4 = new Answers();
            answer4.setText(allRequestParams.get("q4"));
            answer4.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer4")));
            answer4.setQuestion(curQuestion);
            answers.add(answer4);

            redirectAttributes.addFlashAttribute("q4", answer4);
        }

        // -----------------validation-------------------

        if (curQuestion.getText().length() == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "questionIncorrect");
            return "redirect:/tutor/newQuestionForm";
        }

        if (answers.size() == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "evenOneAnswerNeeded");
            return "redirect:/tutor/newQuestionForm";
        }

        int checkingNumberOfCorrectAnswers = 0;

        for (Answers i: answers) {
            if (i.isCorrect())
                checkingNumberOfCorrectAnswers++;
        }

        if (checkingNumberOfCorrectAnswers == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "evenOneCorrectAnswerNeeded");
            return "redirect:/tutor/newQuestionForm";
        }

        if (!curQuestion.isMultipleQuestions() && (checkingNumberOfCorrectAnswers > 1)) {

            redirectAttributes.addFlashAttribute("resultMessage", "incorrectNumberOfCorrectAnswers");
            return "redirect:/tutor/newQuestionForm";
        }

        curQuestion.setAnswers(answers);
        questionsService.addNewQuestion(curQuestion);

        logger.debug("User "+((Users)request.getSession().getAttribute("user")).getLogin()+" created question by"+
                curQuestion.getTopic()+": "+curQuestion.getText());

        redirectAttributes.addFlashAttribute("resultMessage", "questionAdded");

        return "redirect:/tutor/";
    }

}
