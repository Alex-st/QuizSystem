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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("isMulti", initialQuestions.get(0).isMultipleQuestions());

        List<String> answers = new ArrayList<>();
        for (Answers i: initialQuestions.get(0).getAnswers()) {
            answers.add(i.getText());
        }
        request.getSession().setAttribute("curQuestion", 0);
        model.addAttribute("answers", answers);

        return "exam";
    }

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/examprocess", method = RequestMethod.POST)
    public String examProcessing(HttpServletRequest request, @RequestParam Map<String,String> allRequestParams, Model model) {

        //---------------checking if button "next" was pushed----------------
        if (allRequestParams.get("send").equals("next")) {
            List<Questions> qList = (List<Questions>)request.getSession().getAttribute("questionsList");
            int curQuestion = (int) request.getSession().getAttribute("curQuestion");

            if (curQuestion == (qList.size()-1)) {
                curQuestion = 0;
            }
            else curQuestion++;

            model.addAttribute("question", qList.get(curQuestion).getText());
            model.addAttribute("isMulti", qList.get(curQuestion).isMultipleQuestions());

            List<String> answers = new ArrayList<>();
            for (Answers i: qList.get(curQuestion).getAnswers()) {
                answers.add(i.getText());
            }
            request.getSession().setAttribute("curQuestion", curQuestion);
            model.addAttribute("answers", answers);

            return "exam";
        }
        //------------------------------------------------------------------------------




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

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
//
//
//        return "studWelcomePage";
//    }

}
