package com.example.toeic.data.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupQuestion {

    public enum GroupQuestionIndex {
        FIRST(0),
        SECOND(1),
        THIRD(2);
        private int value;

        public int getValue() {
            return value;
        }

        GroupQuestionIndex(int value) {
            this.value = value;
        }
    }

    private Integer id;

    private String explainAnswer;

    private String linkImageResource;

    private String linkMp3Resource;

    private String resourceParagraph;

    private Integer partId;

    private Integer examId;

    private List<Question> questions;
}
