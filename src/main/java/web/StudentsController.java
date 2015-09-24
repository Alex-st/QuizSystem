package web;

import dao.domain.*;
import dao.repository.ResultsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.QuestionsService;
import service.ResultsService;
import service.TopicsService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by alex on 9/14/15.
 */
@Controller("studentsController")
@RequestMapping("/stud")
public class StudentsController {

    @Autowired
    ResultsService resultsService;

    @Autowired
    TopicsService topicsService;

    @Autowired
    QuestionsService questionsService;

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(HttpServletRequest request, Model model) {

        String language = (String)request.getSession().getAttribute("locale");
        LangEnum curLang = LangEnum.ENG;

        if (language.equals("ru")) {
            curLang = LangEnum.RU;
        }

        List<Topics> topics = topicsService.getAllTopicsByLanguage(curLang);
        model.addAttribute("topics", topics);

        Users curUser = (Users)request.getSession().getAttribute("user");
        List<Results> studresults = resultsService.getStudentResults(curUser);

        model.addAttribute("studresults", studresults);

        return "studWelcomePage";
    }

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/examinit", method = RequestMethod.GET)
    public String examInit(HttpServletRequest request, @RequestParam("test") int topicId, Model model) {
        Topics curTopic = topicsService.getTopicById(topicId);
        request.getSession().setAttribute("testmark", 0.);
        request.getSession().setAttribute("testtopic", curTopic);

        String language = (String)request.getSession().getAttribute("locale");
        LangEnum curLang = LangEnum.ENG;

        if (language.equals("ru")) {
            curLang = LangEnum.RU;
        }

        List<Questions> initialQuestions = questionsService.getFixedNumberOfQuestions(5, curTopic, curLang);
        request.getSession().setAttribute("questionsList", initialQuestions);

        model.addAttribute("question", initialQuestions.get(0).getText());
        request.getSession().setAttribute("isMulti", initialQuestions.get(0).isMultipleQuestions());

        Map<String, Integer> answers = new HashMap<>();
        for (Answers i: initialQuestions.get(0).getAnswers()) {
            answers.put(i.getText(), i.getAnswerId());
        }
        request.getSession().setAttribute("curQuestion", 0);
        model.addAttribute("answers", answers);

        return "exam";
    }

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/examprocess", method = RequestMethod.POST)
    public String examProcessing(HttpServletRequest request,
                                 //@RequestParam Map<String, Object> allRequestParams,
                                 @RequestParam("send") String isSend,
                                 @RequestParam(value ="answer", required=false) String[] answer,
                                 Model model) {

        List<Questions> qList = (List<Questions>)request.getSession().getAttribute("questionsList");
        int curQuestion = (int) request.getSession().getAttribute("curQuestion");


        //---------------checking if button "next" was pushed----------------
        if (isSend.equals("next")) {

            if (curQuestion == (qList.size()-1)) {
                curQuestion = 0;
            }
            else curQuestion++;

            model.addAttribute("question", qList.get(curQuestion).getText());
            request.getSession().setAttribute("isMulti", qList.get(curQuestion).isMultipleQuestions());

            Map<String, Integer> answers = new HashMap<>();
            for (Answers i: qList.get(curQuestion).getAnswers()) {
                answers.put(i.getText(), i.getAnswerId());
            }
            request.getSession().setAttribute("curQuestion", curQuestion);
            model.addAttribute("answers", answers);

            return "exam";
        }
        //-----------------------processing received answer----------------------------
        List<Answers> receivedAnswers = new ArrayList<>();

        if (!(boolean)request.getSession().getAttribute("isMulti")) {
            if (answer != null) {
                Answers a1 = questionsService.getAnswerById(Integer.valueOf(answer[0]));
                receivedAnswers.add(a1);
            }
        }
        else {
            if (answer != null) {
                for (String i : answer) {
                    Answers a = questionsService.getAnswerById(Integer.valueOf(i));
                    receivedAnswers.add(a);
                }
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

            Results testResult = new Results();
            testResult.setDate(new Date());
            testResult.setMark(testMark);
            testResult.setTopic((Topics)request.getSession().getAttribute("testtopic"));
            testResult.setStudent((Users)request.getSession().getAttribute("user"));

            //resultsService.createNewResult(testResult);
            resultsService.createNewResultWithDeletingPrevious(testResult);

            request.getSession().removeAttribute("testtopic");
            request.getSession().removeAttribute("testmark");
            request.getSession().removeAttribute("curQuestion");
            request.getSession().removeAttribute("isMulti");
            request.getSession().removeAttribute("questionsList");

            if (request.getSession().getAttribute("locale").equals("ru"))
                model.addAttribute("resultMessage", "Результат вашего теста "+testMark);
            else model.addAttribute("resultMessage", "Result of your attempt "+testMark);

            return "studWelcomePage";
        }

        //------------new question data loading----------------------------------------

        model.addAttribute("question", qList.get(curQuestion).getText());
        request.getSession().setAttribute("isMulti", qList.get(curQuestion).isMultipleQuestions());

        Map<String, Integer> answers = new HashMap<>();
        for (Answers i: qList.get(curQuestion).getAnswers()) {
            answers.put(i.getText(), i.getAnswerId());
        }
        request.getSession().setAttribute("curQuestion", curQuestion);
        model.addAttribute("answers", answers);

        return "exam";
    }
//
//    @Secured("ROLE_STUDENT")
//    @RequestMapping(value = "/examnextq", method = RequestMethod.POST)
//    public String nextQuestion(HttpServletRequest request, Model model) {
//
//
//        return "exam";
//    }


}
