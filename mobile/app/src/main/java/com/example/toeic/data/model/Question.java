package com.example.toeic.data.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Question implements Serializable {

    private Integer id;

    private String question;

    private Integer correctAnswer;

    private String explainAnswer;

    private String linkImageResource;

    private String linkMp3Resource;

    private String resourceParagraph;

    private Integer groupQuestionId;

    private Integer partId;

    private Integer examId;

    public List<Answer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplainAnswer() {
        return explainAnswer;
    }

    public void setExplainAnswer(String explainAnswer) {
        this.explainAnswer = explainAnswer;
    }

    public String getLinkImageResource() {
        return linkImageResource;
    }

    public void setLinkImageResource(String linkImageResource) {
        this.linkImageResource = linkImageResource;
    }

    public String getLinkMp3Resource() {
        return linkMp3Resource;
    }

    public void setLinkMp3Resource(String linkMp3Resource) {
        this.linkMp3Resource = linkMp3Resource;
    }

    public String getResourceParagraph() {
        return resourceParagraph;
    }

    public void setResourceParagraph(String resourceParagraph) {
        this.resourceParagraph = resourceParagraph;
    }

    public Integer getGroupQuestionId() {
        return groupQuestionId;
    }

    public void setGroupQuestionId(Integer groupQuestionId) {
        this.groupQuestionId = groupQuestionId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
