package com.example.toeic.model.exam;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "answer")
@Entity
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "answer", nullable = false)
    private String answer;

    @JsonIgnore
    @Transient
    private String index;

    @ManyToOne
    @JoinColumn(name = "question_id") // thông qua khóa ngoại address_id
    @JsonIgnoreProperties("answers")
    private Question question;
}
