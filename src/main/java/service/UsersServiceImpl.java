package service;

import dao.domain.RoleEnum;
import dao.domain.Users;
import dao.domain.UsersPrivateData;
import dao.repository.UsersPrivateDataRepo;
import dao.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/14/15.
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersPrivateDataRepo usersPrivateDataRepository;

    @Autowired
    private UsersRepo usersRepository;


    @Override
    public int createUser(Users user, String pass, RoleEnum role) {

        int generatedId = usersRepository.saveUser(user);
        Users tmp = usersRepository.getUserById(generatedId);

        UsersPrivateData credentials = new UsersPrivateData();
        credentials.setLogin(user.getLogin());
        credentials.setPass(pass);
        credentials.setRole(role);
        credentials.setUser(tmp);

        usersPrivateDataRepository.saveUserCredentials(credentials);
        return generatedId;
    }

    @Override
    public UsersPrivateData getUserCredentials(Users user) {
        return usersPrivateDataRepository.getCredentialsByUser(user);
    }

    @Override
    public List<Users> getAllStudents() {
        return usersPrivateDataRepository.getAllStudents();
    }

    @Override
    public List<Users> getAllTutors() {
        return usersPrivateDataRepository.getAllTutors();
    }

    @Override
    public Users getUserByLogin(String login) {
        return usersRepository.getUserByLogin(login);
    }

    @Override
    public void deleteUser(Users user) {
        UsersPrivateData credentials = usersPrivateDataRepository.getCredentialsByUser(user);
        usersPrivateDataRepository.deleteCredentials(credentials);
        usersRepository.deleteUser(user);
    }



}
