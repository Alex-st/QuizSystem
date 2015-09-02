package dao.repository;

import dao.domain.Questions;

import org.junit.Ignore;
import org.junit.Test;
import service.AppEMFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by alex on 8/17/15.
 */
public class QuestionsRepositoryMethodsTest {

    private QuestionsRepository questionsRepository;
    private UsersRepo usersRepo;

    @Test
    @Ignore
    public void testCreatingQuestion() {

        EntityManagerFactory emf = AppEMFactory.getAppEMFactory();

        EntityManager em = emf.createEntityManager();

        Questions question = new Questions();

        question.setAuthor(usersRepo.getUserById(3));


    }
}
