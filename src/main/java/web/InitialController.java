package web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alex on 9/25/15.
 */
@Controller("initial")
//@RequestMapping("/")
public class InitialController {

    @RequestMapping(value ={"/", "/index"})
    public String registrationPage() {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        if (auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_TUTOR]")){
//////            return "redirect:/tutor/";
////            return "tutorWelcomePage";
////        };
////        if (auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_STUDENT]")){
////            return "redirect:/stud/";
////        };

        return "index";
    }
}
