package web;

import dao.domain.*;
import dao.repository.ResultsRepo;
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
import service.ResultsService;
import service.TopicsService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by alex on 9/14/15.
 */
@Controller("studentsController")
@Secured("ROLE_STUDENT")
@RequestMapping("/stud")
public class StudentsController {

    private static final int NUMBER_OF_QUESTIONS_IN_TEST = 5;

    @Autowired
    ResultsService resultsService;

    @Autowired
    TopicsService topicsService;

    @Autowired
    QuestionsService questionsService;

    private static final Logger logger =
            LoggerFactory.getLogger(StudentsController.class);

    private LangEnum getCurrentLanguage(HttpServletRequest request) {

        String language = (String)request.getSession().getAttribute("locale");
        LangEnum curLang = LangEnum.ENG;

        if (language.equals("ru")) {
            curLang = LangEnum.RU;
        }
        return curLang;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(HttpServletRequest request, Model model) {

        Users curUser = (Users)request.getSession().getAttribute("user");
        List<Results> studresults = resultsService.getStudentResults(curUser);

        model.addAttribute("topics", topicsService.getAllTopicsByLanguage(getCurrentLanguage(request)));
        model.addAttribute("studresults", studresults);

        return "studWelcomePage";
    }

    @RequestMapping(value = "/examinit", method = RequestMethod.GET)
    public String examInit(HttpServletRequest request, @RequestParam("test") int topicId, Model model) {
        Topics curTopic = topicsService.getTopicById(topicId);
        request.getSession().setAttribute("testtopic", curTopic);

        request.getSession().setAttribute("testmark", 0.);

        List<Questions> initialQuestions = questionsService.getFixedNumberOfQuestions(NUMBER_OF_QUESTIONS_IN_TEST,
                                           curTopic, getCurrentLanguage(request));
        request.getSession().setAttribute("questionsList", initialQuestions);

        Map<String, Integer> answers = nextQuestion(0, initialQuestions, model, request);
        model.addAttribute("answers", answers);

        logger.debug("User "+((Users)request.getSession().getAttribute("user")).getLogin()+" started test "+
                ((Topics)(Topics) request.getSession().getAttribute("testtopic")).getTopicName(), "admin");

        return "exam";
    }

    @RequestMapping(value = "/examprocess", method = RequestMethod.POST)
    public String examProcessing(HttpServletRequest request,
                                 @RequestParam("send") String isSend,
                                 @RequestParam(value ="answer", required=false) String[] answer,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        //-------------check whether test was initiated correctly-------------
        if (!request.getHeader("Referer").contains("examprocess") && !request.getHeader("Referer").contains("examinit")) {
            model.addAttribute("resultMessage", "needRestartExam");
            return "studWelcomePage";
        }

        List<Questions> qList = (List<Questions>)request.getSession().getAttribute("questionsList");
        int curQuestion = (int) request.getSession().getAttribute("curQuestion");

        //---------------checking if button "next" was pushed----------------
        if (isSend.equals("next")) {

            if (curQuestion == (qList.size()-1)) {
                curQuestion = 0;
            }
            else curQuestion++;

            Map<String, Integer> answers = nextQuestion(curQuestion, qList, model, request);
            model.addAttribute("answers", answers);

            return "exam";
        }
        //-----------------------processing received answer----------------------------
        List<Answers> receivedAnswers = new ArrayList<>();

        if (answer != null) {
            for (String i : answer) {
                Answers a = questionsService.getAnswerById(Integer.valueOf(i));
                receivedAnswers.add(a);
            }
        }

        double curQuestionMark = questionsService.countMarkForReceivedQuestionAnswers
                (qList.get(curQuestion), receivedAnswers);

        double testMark = (double)request.getSession().getAttribute("testmark")+curQuestionMark;
        request.getSession().setAttribute("testmark", testMark);

        qList.remove(curQuestion);
        request.getSession().setAttribute("questionsList", qList);

        if (curQuestion == qList.size()) {
            curQuestion = 0;
        }

        //---------------processing finishing of test------------------------
        if(qList.size() == 0) {
            return finishTest(request, redirectAttributes, testMark);
        }

        //------------new question data loading----------------------------------------
        Map<String, Integer> answers = nextQuestion(curQuestion, qList, model, request);
        model.addAttribute("answers", answers);

        return "exam";
    }

    private String finishTest(HttpServletRequest request, RedirectAttributes redirectAttributes, double testMark) {
        Results testResult = new Results();
        testResult.setDate(new Date());
        testResult.setMark(testMark);
        testResult.setTopic((Topics)request.getSession().getAttribute("testtopic"));
        testResult.setStudent((Users)request.getSession().getAttribute("user"));

        //resultsService.createNewResult(testResult);
        resultsService.createNewResultWithDeletingPrevious(testResult);

        logger.debug("User "+((Users)request.getSession().getAttribute("user")).getLogin()+" finished test "+
                ((Topics)(Topics) request.getSession().getAttribute("testtopic")).getTopicName()
                +" with result "+testMark, "admin");

        request.getSession().removeAttribute("testtopic");
        request.getSession().removeAttribute("testmark");
        request.getSession().removeAttribute("curQuestion");
        request.getSession().removeAttribute("isMulti");
        request.getSession().removeAttribute("questionsList");

        redirectAttributes.addFlashAttribute("resultMessage", "resultNotification");
        redirectAttributes.addFlashAttribute("resultMark", testMark);
        return"redirect:/stud/";
    }

    //method prepares "curQuestion"
    //and returns list of its answers
    private Map<String, Integer> nextQuestion(int curQuestion, List<Questions> qList,
                                              Model model, HttpServletRequest request) {

        model.addAttribute("question", qList.get(curQuestion).getText());
        request.getSession().setAttribute("isMulti", qList.get(curQuestion).isMultipleQuestions());

        Map<String, Integer> answers = new HashMap<>();
        for (Answers i: qList.get(curQuestion).getAnswers()) {
            answers.put(i.getText(), i.getAnswerId());
        }
        request.getSession().setAttribute("curQuestion", curQuestion);
        return answers;
    }

}
