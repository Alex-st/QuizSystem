package web;

import dao.domain.Users;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.UsersService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 9/27/15.
 */
@Component
public class UserValidator implements Validator {

    private UsersService usersService;

    private boolean isUserNew;

    public boolean isUserNew() {
        return isUserNew;
    }

    public void setIsUserNew(boolean isUserNew) {
        this.isUserNew = isUserNew;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users user = (Users) target;

        if(!isEmailValid(user.getEmail())){
            //errors.rejectValue("email","adresse_mail.notValid","Not a valid email");
            errors.rejectValue("email","emailError","emailisincorrect");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "nameError", "nameisincorrect");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "loginError", "loginisincorrect");

        if (isUserNew && (usersService.getAllLogins().contains(user.getLogin()))) {
            errors.rejectValue("login","loginError","loginexist");
        };
    }

    public boolean isEmailValid(String emailAddress){
        String expression ="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        //String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
}
