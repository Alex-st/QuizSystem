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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(HttpServletRequest request, Model model) {


        Users curUser = (Users)request.getSession().getAttribute("user");
        List<Questions> curQuestions = questionsService.getQuestionsByAuthor(curUser);
        model.addAttribute("questions", curQuestions);

        return "tutorWelcomePage";
    }

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

        //---------------Processing received answers -------------------------
        for (int i = 1; i < 5; i++) {
            if (allRequestParams.get("q"+i).length() > 0) {
                proccesSingleAnswerInput(answers, allRequestParams, redirectAttributes, curQuestion, i);
            }
        }

        // -----------------validation---------------------------------------
        if (!isQuestionDataValid(curQuestion, redirectAttributes, answers)) {
            return "redirect:/tutor/newQuestionForm";
        }
        // ------------------End of validation ------------------------------

        curQuestion.setAnswers(answers);
        questionsService.addNewQuestion(curQuestion);

        logger.debug("User "+((Users)request.getSession().getAttribute("user")).getLogin()+" created question by"+
                curQuestion.getTopic()+": "+curQuestion.getText());

        redirectAttributes.addFlashAttribute("resultMessage", "questionAdded");

        return "redirect:/tutor/";
    }

    private void proccesSingleAnswerInput(Set<Answers> answers,
                                           @RequestParam Map<String,String> allRequestParams,
                                           RedirectAttributes redirectAttributes, Questions curQuestion,
                                           int answerInputId) {
        Answers answer = new Answers();
        answer.setText(allRequestParams.get("q"+answerInputId));
        answer.setIsCorrect(Boolean.valueOf(allRequestParams.get("correctAnswer"+answerInputId)));
        answer.setQuestion(curQuestion);
        answers.add(answer);

        redirectAttributes.addFlashAttribute("q"+answerInputId, answer);
    }

    private boolean isQuestionDataValid(Questions curQuestion, RedirectAttributes redirectAttributes, Set<Answers> answers) {

        if (curQuestion.getText().length() == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "questionIncorrect");
            return false;
        }

        if (answers.size() == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "evenOneAnswerNeeded");
            return false;
        }

        int checkingNumberOfCorrectAnswers = 0;

        for (Answers i: answers) {
            if (i.isCorrect())
                checkingNumberOfCorrectAnswers++;
        }

        if (checkingNumberOfCorrectAnswers == 0) {
            redirectAttributes.addFlashAttribute("resultMessage", "evenOneCorrectAnswerNeeded");
            return false;
        }

        if (!curQuestion.isMultipleQuestions() && (checkingNumberOfCorrectAnswers > 1)) {

            redirectAttributes.addFlashAttribute("resultMessage", "incorrectNumberOfCorrectAnswers");
            return false;
        }

        return true;
    }

}
