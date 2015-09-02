package dao.domain;

import javax.persistence.*;

/**
 * Entity bean with JPA annotations
 * Created by alex on 8/13/15.
 */
@Entity
public class Answers {
    @Id
    @GeneratedValue
    private int answerId;

    //id -> questionId
    @ManyToOne
    @JoinColumn(name = "id")
    private Questions question;

    @Column(nullable = false)
    private String text;

    private boolean isCorrect;

    public Answers() {
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
