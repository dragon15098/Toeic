package com.example.toeic.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Exam implements Serializable{

    private Integer id;

    private String name;

    public List<Part> parts;
}
