package com.example.toeic.data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class GroupQuestionPoint implements Serializable {
    Map<Integer, List<Boolean>> checkList;
    int totalCorrectAnswer;
    int totalWrongAnswer;
}
