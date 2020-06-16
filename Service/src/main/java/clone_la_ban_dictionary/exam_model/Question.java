package clone_la_ban_dictionary.exam_model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    public Integer correctAnswer;

    public String explainAnswer;

    public Integer id;

    public Integer partId;

    public String question;

    public List<Answer> answers;

    public String linkImageResource;

    public String linkMp3Resource;

    public String resourceParagraph;
}
