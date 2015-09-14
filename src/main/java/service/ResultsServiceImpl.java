package service;

import dao.domain.Results;
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
    public List<Results> getAllResults() {
        return resultsRepository.getAllResults();
    }
}
