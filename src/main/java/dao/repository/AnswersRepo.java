package dao.repository;

import dao.domain.Answers;

import javax.transaction.Transactional;

/**
 * Created by alex on 9/2/15.
 */
public interface AnswersRepo {
    @Transactional
    int saveAnswer(Answers answer);

    @Transactional
    void updateAnswer(Answers answer);

    @Transactional
    void deleteAnswer(Answers answer);

    Answers getAnswerById(int id);
}
