package com.example.toeic.data.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Answer implements Serializable {

    private Integer id;

    private String answer;

    private String index;

    private Question question;

    public enum AnswerIndex {
        FRIST(0), SECOND(1), THIRD(2), FOUR(3);
        private int value;

        AnswerIndex(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
