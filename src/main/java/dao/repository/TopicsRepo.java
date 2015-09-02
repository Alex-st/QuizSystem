package dao.repository;

import dao.domain.LangEnum;
import dao.domain.Topics;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by alex on 9/2/15.
 */
public interface TopicsRepo {
    List<Topics> getAllTopics();

    List<Topics> getLanguageTopics(LangEnum lang);

    @Transactional
    int saveTopic(Topics topic);

    @Transactional
    void deleteTopic(Topics topic);

    Topics getTopicById(int id);
}
