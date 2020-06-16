package com.example.toeic.model.exam;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Table(name = "exam")
@Data
@Entity
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    public List<Part> parts;
}
