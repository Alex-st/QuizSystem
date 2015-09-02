package dao.repository;

import dao.domain.Users;
import dao.domain.UsersPrivateData;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
public interface UsersPrivateDataRepo {
    @Transactional
    int saveUserCredentials(UsersPrivateData credentials);

    @Transactional
    void deleteCredentials(UsersPrivateData credentials);

    List<Users> getAllStudents();

    List<Users> getAllTutors();
}
