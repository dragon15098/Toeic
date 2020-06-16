package com.example.toeic.model.exam;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "part")
public class Part implements Serializable {
    private static final long serialVersionUID = 1L;


    public enum PartIndex {
        PART_ONE(1),
        PART_TWO(2),
        PART_THREE(3),
        PART_FOUR(4),
        PART_FIVE(5),
        PART_SIX(6),
        PART_SEVEN(7);
        private int value;

        public int getValue() {
            return value;
        }

        PartIndex(int value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    public List<Question> questions;

    @Transient
    public List<GroupQuestion> groupQuestions;


}
