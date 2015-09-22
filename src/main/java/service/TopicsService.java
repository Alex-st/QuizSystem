package service;

import dao.domain.LangEnum;
import dao.domain.Topics;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
public interface TopicsService {
    List<Topics> getAllTopicsByLanguage(LangEnum lang);

    List<Topics> getAllTopics();

    int addTopic(Topics topic);

    Topics getTopicById(int id);

    void removeTopic(Topics topic);

    Topics getTopicByName(String topicName);
}
