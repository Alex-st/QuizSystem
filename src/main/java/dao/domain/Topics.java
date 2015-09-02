package dao.domain;

import javax.persistence.*;

/**
 * Entity bean with JPA annotations
 * Created by alex on 8/13/15.
 */
@Entity
public class Topics {
        @Id
        @GeneratedValue
        private int topicId;

        @Column(nullable = false)
        private String topicName;

        private String topicDesc;

        @Enumerated(EnumType.STRING)
        private LangEnum topicLanguage;

        public Topics() {

        }

        public Topics(int id, String name, String desc, LangEnum lang) {
            this.topicId = id;
            topicName = name;
            topicDesc = desc;
            topicLanguage = lang;
        }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
            return topicName;
        }

    public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

    public String getTopicDesc() {
            return topicDesc;
        }

    public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

    public LangEnum getTopicLanguage() {
            return topicLanguage;
        }

    public void setTopicLanguage(LangEnum topicLanguage) {
            this.topicLanguage = topicLanguage;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topics topic = (Topics) o;

        //if (topicId != topic.topicId) return false;
        if (topicName != null ? !topicName.equals(topic.topicName) : topic.topicName != null) return false;
        if (topicDesc != null ? !topicDesc.equals(topic.topicDesc) : topic.topicDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        //int result = topicId;
        result = 31 * result + (topicName != null ? topicName.hashCode() : 0);
        result = 31 * result + (topicDesc != null ? topicDesc.hashCode() : 0);
        return result;
    }

    public String toString() {
        return (topicId+" : "+topicName+" / "+topicDesc+" / "+topicLanguage);
    }
}
