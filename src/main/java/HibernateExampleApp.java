import dao.domain.*;
import dao.repository.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.DataSourceConnectionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UsersService;

import javax.activation.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 7/30/15.
 */
public class HibernateExampleApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");

        appContext.getBeanDefinitionNames();

        UsersService usersService = appContext.getBean("usersService", UsersService.class);

        Users testUser1 = new Users();
        testUser1.setSurname("Cидоров");
        testUser1.setLogin("testUser2");
        testUser1.setEmail("mail@i.ua");
        testUser1.setName("Иван");

        System.out.println(testUser1.getName());

//        BasicDataSource dataSource = appContext.getBean("dataSource", BasicDataSource.class);
//        try {
//            Connection conn = dataSource.getConnection();
//
//
//            PreparedStatement statement=
//                    conn.prepareStatement(
//                            "INSERT INTO Topics (topicDesc, topicName, topicLanguage) VALUES (?,?,?)");
//            System.out.println(statement == null);
//
//                //statement.setInt(1, i.getIdstudents());
//                statement.setString(1, "Тестовая тема");
//                statement.setString(2, "Математика");
//                statement.setString(3, "RU");
//               // statement.setString(4, "черезДБС");
//
//
////            PreparedStatement stmt = conn.prepareStatement("select * from Topics");
////
////            ResultSet rs = stmt.executeQuery();
////            while (rs.next()) {
////                System.out.println("topic: " + rs.getString("topicName"));
////            }
////
//            statement.executeUpdate();
//            statement.close();
//            conn.close();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        int generatedId = usersService.createUser(testUser1,"test",RoleEnum.ROLE_STUDENT);
        System.out.println(generatedId);

//        Users userToDel = usersService.getUserByLogin("testUser1");
//        usersService.deleteUser(userToDel);

//        TopicsRepo topicsRepository = appContext.
//                getBean("topicsRepository", TopicsRepo.class);
//        QuestionsRepo questionsRepository = appContext.
//                getBean("questionsRepository", QuestionsRepo.class);
//        List<Topics> topicsList = topicsRepository.getAllTopics();
//
//        System.out.println("0 element:"+topicsRepository.getAllTopics().get(0));
//
//        for (Topics i: topicsList) {
//            System.out.println(i.getTopicName());
//        }
//
//        System.out.println(topicsRepository.getAllTopics().get(0));

//        Questions temp = questionsRepository.getQuestionById(1);
//        questionsRepository.deleteQuestion(temp);

//        Questions question = new Questions();
//        question.setText("What does term \'Integration\' mean?");
//        question.setLanguage(LangEnum.ENG);
//        question.setIsMultipleQuestions(false);
//
//        question.setTopic(topicsRepository.getAllTopics().get(0));
//        //question.setAuthor();
//
//        Answers answer1 = new Answers();
//        answer1.setText("Operation inverted to differentiation");
//        answer1.setIsCorrect(true);
//        answer1.setQuestion(question);
//
//        Answers answer2 = new Answers();
//        answer2.setText("Derivative calculation process");
//        answer2.setIsCorrect(false);
//        answer2.setQuestion(question);
//
//        Answers answer3 = new Answers();
//        answer3.setText("Logarithm extracting operation");
//        answer3.setIsCorrect(false);
//        answer3.setQuestion(question);
//
//        Set<Answers> answers = new HashSet<>();
//        answers.add(answer1);
//        answers.add(answer2);
//        answers.add(answer3);
//
//        question.setAnswers(answers);
//        System.out.println("question generated:---------");
//        int id = questionsRepository.saveQuestion(question);
//        System.out.println(id);




//        OrderService orderService = appContext.
//                getBean("orderService", OrderService.class);
//
//        Order newOrder1 = orderService.createNewOrder();
//        newOrder1.addItems(pizzas.get(0), pizzas.get(1));






//        EntityManagerFactory emf = AppEMFactory.getAppEMFactory();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateExamleUnit");

//        System.out.println(emf==null);
//
//        EntityManager em = emf.createEntityManager();
//
//        System.out.println(em==null);

//        Users temp = new Users();
//        temp.setName("AlexTest");
//        temp.setEmail("alextest@i.ua");
//        temp.setLogin("alextest");
//        temp.setPass("test");
//        temp.setRole(RoleEnum.TUTOR);
//
//        try {
//            em.getTransaction().begin();
//            em.persist(temp);
//
//            temp = em.find(Users.class, 1);
//            System.out.println(temp);
//
//            em.getTransaction().commit();
//        } finally {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//        }
//
//        System.out.println(temp.toString());
//
//        em.close();
//        emf.close();




//        TopicsRepo topicsRepo = new TopicsRepository(em);
//        Topics myTestTopic = new Topics();
//        myTestTopic.setTopicName("Test");
//        myTestTopic.setTopicDesc("This is Test topic");
//        myTestTopic.setTopicLanguage(LangEnum.ENG);
//        System.out.println(myTestTopic);
//
//        int topicId = topicsRepo.saveTopic(myTestTopic);
//
//        Topics mySecondTestTopic = em.find(Topics.class, topicId);
//        mySecondTestTopic.toString();
//
//        System.out.println("------------------------------------");
//        System.out.println(mySecondTestTopic);
////        System.out.println(mySecondTestTopic.equals(myTestTopic));
//        System.out.println("------------------------------------");
//        topicsRepo.deleteTopic(mySecondTestTopic);
//        System.out.println("Delete finished --------------------");
//        System.out.println(myTestTopic);
//
//        em.close();
//        emf.close();
    }

}
