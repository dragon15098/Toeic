package com.example.toeic.feature.exam.part_exam.question;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Question;

import java.util.List;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class PartQuestionExamPresentImpl extends BasePresenterImpl implements PartQuestionExamPresent {
    public PartQuestionExamView partQuestionExamView;
    public List<Question> questions;

    private int currentQuestion = 0;

    private int point = 0;

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
        Question currentQuestion = getCurrentQuestion();
        if (answer.equals(currentQuestion.getCorrectAnswer())) {
            point++;
        }
        partQuestionExamView = (PartQuestionExamView) view;
        partQuestionExamView.showCorrectAnswer();
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
    public void nextQuestion() {
        if (!isLastQuestion()) {
            currentQuestion++;
            partQuestionExamView.notifyView();
        }
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
