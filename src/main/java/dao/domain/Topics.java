package dao.domain;

import javax.persistence.*;

/**
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

        public int getIdtopics() {
            return topicId;
        }

        public void setIdtopics(int idtopics) {
            this.topicId = idtopics;
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

}
