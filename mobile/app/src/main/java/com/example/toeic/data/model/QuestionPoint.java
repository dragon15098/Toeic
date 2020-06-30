package com.example.toeic.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class QuestionPoint implements Serializable {
    List<Boolean> checkList;
    List<Integer> answer;
    int totalCorrectAnswer;
    int totalWrongAnswer;
}
