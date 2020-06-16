package clone_la_ban_dictionary.exam_model;

import java.io.Serializable;
import java.util.List;

public class Resource implements Serializable {
    public enum ResourceType {
        IMAGE("IMAGE"), MP3("MP3"), PARAGRAPH("PARAGRAPH");
        private String type;

        ResourceType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum ResourceReferenceType {
        QUESTION("QUESTION"), WORD("WORD");
        private String referenceType;

        ResourceReferenceType(String referenceType) {
            this.referenceType = referenceType;
        }

        public String getType() {
            return referenceType;
        }
    }

    public static final long serialVersionUID = 1L;

    public Integer id;

    public String paragraph;

    public String path;

    public Integer referenceId;

    public String referenceType;

    public String type;


}
