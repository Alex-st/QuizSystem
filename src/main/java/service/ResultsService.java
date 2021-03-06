package service;

import dao.domain.Results;
import dao.domain.Topics;
import dao.domain.Users;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
public interface ResultsService {
    int createNewResult(Results result);

    void deleteResult(Results result);

    List<Results> getStudentResults(Users student);

    List<Results> getResultsByStudentAndTopic(Users student, Topics topic);

    List<Results> getAllResults();

    int createNewResultWithDeletingPrevious(Results result);

    List<Results> getResultsByUserAndTopic(Users user, Topics topic);
}
