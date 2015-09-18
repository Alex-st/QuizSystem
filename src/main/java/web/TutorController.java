package web;

import dao.domain.LangEnum;
import dao.domain.Questions;
import dao.domain.Topics;
import dao.domain.Users;
import dao.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.QuestionsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by alex on 9/18/15.
 */
@Controller("tutorController")
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    QuestionsService questionsService;

    @Autowired
    TopicsRepo topicsRepository;

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

        List<Topics> topics = topicsRepository.getLanguageTopics(curLang);

        model.addAttribute("topics", topics);

        return "newQuestion";
    }

    @Secured("ROLE_TUTOR")
    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
    public String createNewQuestion(HttpServletRequest request, Model model) {

        return "tutorWelcomePage";
    }

}
