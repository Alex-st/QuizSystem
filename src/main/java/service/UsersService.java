package service;

import dao.domain.RoleEnum;
import dao.domain.Users;
import dao.domain.UsersPrivateData;

import java.util.List;

/**
 * Created by alex on 9/14/15.
 */
public interface UsersService {
    int createUser(Users user, String pass, RoleEnum role);

    void updateUser(Users user, String pass);

    UsersPrivateData getUserCredentials(Users user);

    List<Users> getAllStudents();

    List<Users> getAllTutors();

    Users getUserByLogin(String login);

    void deleteUser(Users user);

    List<String> getAllLogins();
}
