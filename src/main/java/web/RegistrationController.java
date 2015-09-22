package web;

import dao.domain.RoleEnum;
import dao.domain.Users;
import dao.domain.UsersPrivateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String registrationPage(HttpServletRequest request) {

        request.getSession().setAttribute("locale", request.getParameter("locale"));

        return "registration";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String registrationMethod(@RequestParam Map<String,String> allRequestParams, HttpServletRequest request, Model model) {
        boolean isNewUser = true;
        Users curUser = new Users();

        if (request.getSession().getAttribute("user") != null) {
            isNewUser = false;
            curUser = (Users)request.getSession().getAttribute("user");
        };
//        System.out.println(allRequestParams.get("login"));
//        System.out.println(usersService.getUserByLogin(allRequestParams.get("login")));

        if (isNewUser && (usersService.getUserByLogin(allRequestParams.get("login")) != null)) {
            model.addAttribute("resultMessage", "loginisincorrect");
            return "redirect:";
        }

        try {
            curUser.setName(new String(allRequestParams.get("name").getBytes ("iso-8859-1"), "UTF-8"));
            curUser.setLogin(new String(allRequestParams.get("login").getBytes ("iso-8859-1"), "UTF-8"));
            curUser.setSurname(new String(allRequestParams.get("surname").getBytes ("iso-8859-1"), "UTF-8"));
            curUser.setEmail(new String(allRequestParams.get("email").getBytes ("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RoleEnum role = RoleEnum.ROLE_STUDENT;

        if (isNewUser && allRequestParams.get("select").equals("tutor")) {
            role = RoleEnum.ROLE_TUTOR;
        }

        if (isNewUser) {
            usersService.createUser(curUser, allRequestParams.get("password"), role);
            model.addAttribute("resultMessage", new String("registrationSuccessfull"));
        }
        else {

            System.out.println(curUser.getName()+"/"+curUser.getEmail()+"/"+curUser.getUserId()+"/"+
            curUser.getLogin()+"/"+curUser.getSurname());

            usersService.updateUser(curUser, allRequestParams.get("password"));
            model.addAttribute("resultMessage", new String("User has been updated"));
        }


//        for(Map.Entry<String, String> i: allRequestParams.entrySet()) {
//            System.out.println(i.getKey()+"/"+i.getValue());
//        }

        return "redirect:";
    }

}
