package web;

import dao.domain.RoleEnum;
import dao.domain.Users;
import dao.domain.UsersPrivateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by alex on 9/17/15.
 */
@Controller("registration")
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registrationPage(HttpServletRequest request, Model model) {

        if (request.getParameter("locale") != null) {
            request.getSession().setAttribute("locale", request.getParameter("locale"));
        }

        if (request.getSession().getAttribute("user") != null) {
            model.addAttribute("registerButton", "updateButton");
        }
        else {
            model.addAttribute("registerButton", "registrationButton");
        }

        return "registration";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String registrationMethod(@RequestParam Map<String,String> allRequestParams,
                                     HttpServletRequest request, Model model,
                                     RedirectAttributes redirectAttributes,
                                     Authentication auth) {
        //----------parameter initialization--------------------------
        boolean isNewUser = true;
        Users curUser = new Users();
        RoleEnum role = RoleEnum.ROLE_STUDENT;

        if (request.getSession().getAttribute("user") != null) {
            isNewUser = false;
            curUser = (Users)request.getSession().getAttribute("user");
            role = usersService.getUserCredentials(curUser).getRole();
        }

        //----------checking whether user with such input login is already exist-----
        //todo add parameters to redirectAttributes

        if (isNewUser && (usersService.getAllLogins().contains(allRequestParams.get("login")))) {
            System.out.println("Huston we got problems");
//            redirectAttributes.addFlashAttribute("resultMessage", "loginisincorrect");
//            redirectAttributes.addFlashAttribute("registerButton", "registrationButton");
            return "redirect:";
        }

        if (!isNewUser && !curUser.getLogin().equals(allRequestParams.get("login"))
                && (usersService.getAllLogins().contains(allRequestParams.get("login")))) {
            System.out.println("Huston we got problems too");
//            redirectAttributes.addFlashAttribute("resultMessage", "loginisincorrect");
//            redirectAttributes.addFlashAttribute("registerButton", "registrationButton");
            return "redirect:";
        }


        // ----------------creating new user with received data------------------------

        curUser.setName(allRequestParams.get("name"));
        curUser.setLogin(allRequestParams.get("login"));
        curUser.setSurname(allRequestParams.get("surname"));
        curUser.setEmail(allRequestParams.get("email"));

        if (isNewUser && allRequestParams.get("select").equals("tutor")) {
            role = RoleEnum.ROLE_TUTOR;
        }

        if (isNewUser) {
            usersService.createUser(curUser, allRequestParams.get("password"), role);
            redirectAttributes.addFlashAttribute("resultMessage", "registrationSuccessfull");
        } else {
            usersService.updateUser(curUser, allRequestParams.get("password"));
            redirectAttributes.addFlashAttribute("resultMessage", "updateSuccessfull");


            if (auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_TUTOR]")){
                return "redirect:/tutor/";
            };
            if (auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_STUDENT]")){
                return "redirect:/stud/";
            };

        }


        return "redirect:/index";
    }

}
