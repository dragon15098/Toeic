package com.example.toeic.model.exam;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Table(name = "question")
@Data
@Entity
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "correct_answer", nullable = false)
    private Integer correctAnswer;

    @Column(name = "explain_answer")
    private String explainAnswer;

    @Column(name = "link_image_resource")
    private String linkImageResource;

    @Column(name = "link_mp3_resource")
    private String linkMp3Resource;

    @Column(name = "resource_paragraph")
    private String resourceParagraph;

//    @Column(name = "group_question_id", nullable = false)
//    private Integer groupQuestionId;

    @Column(name = "part_id", nullable = false)
    private Integer partId;

    @Column(name = "exam_id", nullable = false)
    private Integer examId;

    @ManyToOne
    @JoinColumn(name = "group_question_id") // thông qua khóa ngoại address_id
    @JsonIgnoreProperties({"questions"})
    private GroupQuestion groupQuestion;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    public  List<Answer> answers;

}
