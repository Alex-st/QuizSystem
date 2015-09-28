package web;

import dao.domain.RoleEnum;
import dao.domain.Users;
import dao.domain.UsersPrivateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 9/17/15.
 */
@Controller("registration")
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UsersService usersService;

    private static final Logger logger =
            LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registrationPage(HttpServletRequest request, Model model) {

//        logger.debug("User Registration page");

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
        UserValidator userValidator = new UserValidator();

        if (request.getSession().getAttribute("user") != null) {
            isNewUser = false;
            curUser = (Users)request.getSession().getAttribute("user");
            role = usersService.getUserCredentials(curUser).getRole();
        }

        userValidator.setIsUserNew(isNewUser);
        userValidator.setUsersService(usersService);

        // if current existed user change his login to some other already presented in DB
        if (!isNewUser && !curUser.getLogin().equals(allRequestParams.get("login"))
                && (usersService.getAllLogins().contains(allRequestParams.get("login")))) {
            redirectAttributes.addFlashAttribute("resultMessage", "loginexist");
            redirectAttributes.addFlashAttribute("registerButton", "registrationButton");
            return "redirect:/register/";
        }

        // ----------------creating new user with received data------------------------

        curUser.setName(allRequestParams.get("name"));
        curUser.setLogin(allRequestParams.get("login"));
        curUser.setSurname(allRequestParams.get("surname"));
        curUser.setEmail(allRequestParams.get("email"));

        redirectAttributes.addFlashAttribute("user", curUser);

        DataBinder binder = new DataBinder(curUser);
        binder.setValidator(userValidator);
        binder.validate();

        //userValidator.validate(curUser, result);
        BindingResult results = binder.getBindingResult();

        if (results.hasErrors()) {

            Map<String, String> errorMessages = new HashMap<>();

            List<FieldError> errors = results.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getCode() + " - " + error.getDefaultMessage());
                errorMessages.put(error.getCode(), error.getDefaultMessage());

            }

            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/register/";
        }

        if (isNewUser && allRequestParams.get("select").equals("tutor")) {
            role = RoleEnum.ROLE_TUTOR;
        }

        if (allRequestParams.get("password").length() < 6) {
            redirectAttributes.addFlashAttribute("passwordError", "passisincorrect");
            redirectAttributes.addFlashAttribute("registerButton", "registrationButton");
            return "redirect:/register/";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(allRequestParams.get("password"));

        if (isNewUser) {
            //usersService.createUser(curUser, allRequestParams.get("password"), role);
            usersService.createUser(curUser, hashedPassword, role);
            redirectAttributes.addFlashAttribute("resultMessage", "registrationSuccessfull");

            logger.debug("User "+curUser+" has been registered");
        } else {
            //usersService.updateUser(curUser, allRequestParams.get("password"));
            usersService.updateUser(curUser, hashedPassword);
            redirectAttributes.addFlashAttribute("resultMessage", "updateSuccessfull");

            logger.debug("User "+curUser+" has been registered");

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
