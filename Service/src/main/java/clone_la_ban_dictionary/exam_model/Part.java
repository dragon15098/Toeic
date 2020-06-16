package clone_la_ban_dictionary.exam_model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Part implements Serializable {
    private static final long serialVersionUID = 1L;
    public Long id;
    public String name;
    public List<Question> questions;
    public List<GroupQuestion> groupQuestions;

    public Part(Long id, String name) {
        this.id = id;
        this.name = name;
        questions = new ArrayList<>();
    }
}
