package service;

import dao.domain.LangEnum;
import dao.domain.Topics;
import dao.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alex on 9/7/15.
 */
@Service("topicsService")
public class TopicsSeviceImpl implements TopicsService{

    @Autowired
    private TopicsRepo topicsRepository;

    @Override
    public List<Topics> getAllTopicsByLanguage(LangEnum lang) {
        return topicsRepository.getLanguageTopics(lang);
    }

    @Override
    public List<Topics> getAllTopics() {
        return topicsRepository.getAllTopics();
    }

    @Override
    public int addTopic(Topics topic){
        return topicsRepository.saveTopic(topic);
    }

    @Override
    public Topics getTopicById(int id) {
        return topicsRepository.getTopicById(id);
    }

    @Override
    public void removeTopic(Topics topic) {
        topicsRepository.deleteTopic(topic);
    }

    @Override
    public Topics getTopicByName(String topicName) {
        return topicsRepository.getTopicByName(topicName);
    }

}
