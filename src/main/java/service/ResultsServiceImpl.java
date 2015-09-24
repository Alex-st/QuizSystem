package service;

import dao.domain.Results;
import dao.domain.Topics;
import dao.domain.Users;
import dao.repository.ResultsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
@Service("resultsService")
public class ResultsServiceImpl implements ResultsService {

    @Autowired
    private ResultsRepo resultsRepository;

    @Override
    public int createNewResult(Results result){
        return resultsRepository.saveUserResult(result);
    }

    @Override
    public void deleteResult(Results result) {
        resultsRepository.deleteUserResult(result);
    }

    @Override
    public List<Results> getStudentResults(Users student) {
       return resultsRepository.getAllStudentResults(student);
    }

    @Override
    public List<Results> getResultsByStudentAndTopic(Users student, Topics topic) {
        return resultsRepository.getResultsByStudentAndTopic(student, topic);
    }

    @Override
    public List<Results> getAllResults() {
        return resultsRepository.getAllResults();
    }

    @Override
    public int createNewResultWithDeletingPrevious(Results result) {
        Topics topic = result.getTopic();
        Users student = result.getStudent();
        List<Results> prevResults = getResultsByStudentAndTopic(student, topic);

        if (prevResults!=null && prevResults.size() > 0) {
            deleteResult(prevResults.get(0));
        }

        return resultsRepository.saveUserResult(result);

    }

    @Override
    public List<Results> getResultsByUserAndTopic(Users user, Topics topic) {
        return resultsRepository.getResultsByStudentAndTopic(user, topic);
    }
}
