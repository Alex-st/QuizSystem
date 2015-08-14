package dao.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alex on 8/13/15.
 */

@Entity
public class Results {
    @Id
    @GeneratedValue
    private int resultId;

    private double mark;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users author;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private Topics topic;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Results() {
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }
}
