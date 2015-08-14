package dao.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by alex on 8/13/15.
 */
@Entity
public class Questions {

    @Id
    @GeneratedValue
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private Topics topic;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LangEnum language;

    @Column(nullable = false)
    private boolean isMultipleQuestions;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users author;

    @OneToMany(mappedBy = "question")
    private Set<Answers> answers;


    public Questions() {
    }

    public int getId() {
        return questionId;
    }

    public void setId(int id) {
        this.questionId = id;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LangEnum getLanguage() {
        return language;
    }

    public void setLanguage(LangEnum language) {
        this.language = language;
    }

    public boolean isMultipleQuestions() {
        return isMultipleQuestions;
    }

    public void setIsMultipleQuestions(boolean isMultipleQuestions) {
        this.isMultipleQuestions = isMultipleQuestions;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Set<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answers> answers) {
        this.answers = answers;
    }
}
