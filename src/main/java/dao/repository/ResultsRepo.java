package dao.repository;

import dao.domain.Results;
import dao.domain.Users;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
public interface ResultsRepo {
    @Transactional
    int saveUserResult(Results res);

    @Transactional
    void deleteUserResult(Results res);

    List<Results> getAllResults();

    List<Results> getAllStudentResults(Users user);
}
