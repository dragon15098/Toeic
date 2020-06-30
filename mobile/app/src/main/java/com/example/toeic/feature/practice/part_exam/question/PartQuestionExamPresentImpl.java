package com.example.toeic.feature.practice.part_exam.question;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Question;
import com.example.toeic.data.model.QuestionPoint;

import java.util.ArrayList;
import java.util.List;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class PartQuestionExamPresentImpl extends BasePresenterImpl implements PartQuestionExamPresent {
    public PartQuestionExamView partQuestionExamView;
    public List<Question> questions;

    private int currentQuestion = 0;

    private List<Boolean> checkList = new ArrayList<>();


    private boolean submitted = false;

    int totalCorrectAnswer = 0;
    int totalWrongAnswer = 0;

    @Override
    public String getUrlImage() {
        if (questions != null && !questions.isEmpty()) {
            return questions.get(currentQuestion).getLinkImageResource();
        }
        return EMPTY_STRING;
    }

    @Override
    public String getMp3Link() {
        return questions.get(currentQuestion).getLinkMp3Resource();
    }

    @Override
    public void submitAnswer(Integer answer) {
        submitted = true;
        Question currentQuestion = getCurrentQuestion();
        if (answer.equals(currentQuestion.getCorrectAnswer())) {
            addCorrectAnswer();
        } else {
            addWrongAnswer();
        }
    }

    private void addCorrectAnswer() {
        checkList.add(true);
        totalCorrectAnswer++;
    }

    private void addWrongAnswer() {
        checkList.add(false);
        totalWrongAnswer++;
    }

    @Override
    public String getExplainAnswerA() {
        return getCurrentQuestion().getAnswers().get(Answer.AnswerIndex.FRIST.getValue()).getAnswer();
    }

    @Override
    public String getExplainAnswerB() {
        return getCurrentQuestion().getAnswers().get(Answer.AnswerIndex.SECOND.getValue()).getAnswer();
    }

    @Override
    public String getExplainAnswerC() {
        return getCurrentQuestion().getAnswers().get(Answer.AnswerIndex.THIRD.getValue()).getAnswer();
    }

    @Override
    public String getExplainAnswerD() {
        return getCurrentQuestion().getAnswers().get(Answer.AnswerIndex.FOUR.getValue()).getAnswer();
    }

    @Override
    public int getCorrectAnswer() {
        return getCurrentQuestion().getCorrectAnswer();
    }

    @Override
    public QuestionPoint getQuestionPoint() {
        return QuestionPoint.builder()
                .checkList(checkList)
                .totalCorrectAnswer(totalCorrectAnswer)
                .totalWrongAnswer(totalWrongAnswer)
                .build();
    }

    @Override
    public void nextQuestion() {
        if (!submitted) {
            checkList.add(false);
            addWrongAnswer();
        }
        submitted = false;
        if (isLastQuestion()) {
            endPart();
        } else {
            currentQuestion++;
            partQuestionExamView.notifyView();
        }
    }

    private void endPart() {
        ((PartQuestionExamView) view).endPart();
    }

    @Override
    public void backQuestion() {
        if (currentQuestion != 0) {
            currentQuestion--;
            partQuestionExamView.notifyView();
        }
    }

    private boolean isLastQuestion() {
        return currentQuestion + 1 == questions.size();
    }

    protected Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }
}
