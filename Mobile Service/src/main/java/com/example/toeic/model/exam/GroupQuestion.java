package com.example.toeic.model.exam;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "group_question")
@Entity
@Data
public class GroupQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "explain_answer", nullable = false)
    private String explainAnswer;

    @Column(name = "link_image_resource")
    private String linkImageResource;

    @Column(name = "link_mp3_resource")
    private String linkMp3Resource;

    @Column(name = "resource_paragraph")
    private String resourceParagraph;

    @Column(name = "part_id", nullable = false)
    private Integer partId;

    @Column(name = "exam_id", nullable = false)
    private Integer examId;

    @OneToMany(mappedBy = "groupQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    private List<Question> questions;
}
