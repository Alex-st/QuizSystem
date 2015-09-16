package web;

import dao.repository.ResultsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by alex on 9/14/15.
 */
@Controller("studentsController")
@RequestMapping("/stud")
public class StudentsController {

    @Autowired
    ResultsRepo resultsRepository;

    @Secured("ROLE_STUDENT")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String wellcomePage(Model model) {

        //model.addAttribute("locale", locale);

        return "studWelcomePage";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
//
//
//        return "studWelcomePage";
//    }

}
