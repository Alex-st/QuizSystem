package dao.repository;

import dao.domain.Users;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
public interface UsersRepo {
    List<Users> getAllUsers(Users user);

    @Transactional
    int saveUser(Users user);

    @Transactional
    void updateUser(Users user);

    @Transactional
    void deleteUser(Users user);

    Users getUserById(int id);

    Users getUserByLogin(String login);

    List<Users> getAllUsers();
}
