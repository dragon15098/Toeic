package com.example.toeic.feature.practice.part_exam.group_question;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.GroupQuestionPoint;
import com.example.toeic.data.model.Question;
import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PartGroupQuestionExamPresentImpl extends BasePresenterImpl implements PartGroupQuestionExamPresent {
    public PartGroupQuestionExamView partGroupQuestionExamView;
    public List<GroupQuestion> groupQuestions;

    protected int currentGroupQuestionIndex = 0;

    protected boolean submitted = false;

    private int totalCorrectAnswer = 0;
    private int totalWrongAnswer = 0;

    private Map<Integer, List<Boolean>> pointMapping = new HashMap<>();

    @Override
    public Question getFirstQuestion() {
        return getCurrentGroupQuestionIndex().getQuestions().get(GroupQuestion.GroupQuestionIndex.FIRST.getValue());
    }

    @Override
    public Question getSecondQuestion() {
        return getCurrentGroupQuestionIndex().getQuestions().get(GroupQuestion.GroupQuestionIndex.SECOND.getValue());
    }

    @Override
    public Question getThirdQuestion() {
        List<Question> questions = getCurrentGroupQuestionIndex().getQuestions();
        if (questions.size() >= 3) {
            return getCurrentGroupQuestionIndex().getQuestions().get(GroupQuestion.GroupQuestionIndex.THIRD.getValue());
        }
        return null;
    }

    @Override
    public String getMp3Link() {
        return getCurrentGroupQuestionIndex().getLinkMp3Resource();
    }


    @Override
    public void nextQuestion() {
        if (!submitted) {
            checkFirstQuestion(null);
            checkSecondQuestion(null);
            checkThirdQuestion(null);
        }
        submitted = false;
        if (isLastQuestion()) {
            ((PartGroupQuestionExamView) view).endPart();
        } else {
            currentGroupQuestionIndex++;
            partGroupQuestionExamView.notifyView();
        }
    }

    protected boolean isLastQuestion() {
        return currentGroupQuestionIndex + 1 == groupQuestions.size();
    }

    @Override
    public void backQuestion() {
        if (currentGroupQuestionIndex > 0) {
            currentGroupQuestionIndex--;
            partGroupQuestionExamView.notifyView();
        }
    }

    @Override
    public String getUrlImage() {
        return getCurrentGroupQuestionIndex().getLinkImageResource();
    }

    @Override
    public GroupQuestionPoint getGroupQuestionPoint() {
        return GroupQuestionPoint.builder()
                .checkList(pointMapping)
                .totalCorrectAnswer(totalCorrectAnswer)
                .totalWrongAnswer(totalWrongAnswer)
                .build();
    }

    protected void checkFirstQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestionIndex();
        Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.FIRST.getValue());
        checkAnswer(answer, question);
    }

    protected void checkSecondQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestionIndex();
        Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.SECOND.getValue());
        checkAnswer(answer, question);
    }

    protected void checkThirdQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestionIndex();
        List<Question> questions = currentGroupQuestion.getQuestions();
        if (questions.size() > GroupQuestion.GroupQuestionIndex.THIRD.getValue()) {
            Question question = questions.get(GroupQuestion.GroupQuestionIndex.THIRD.getValue());
            checkAnswer(answer, question);
        }
    }

    protected void checkAnswer(Integer answer, Question question) {
        List<Boolean> checkList;
        if (pointMapping.containsKey(currentGroupQuestionIndex)) {
            checkList = pointMapping.get(currentGroupQuestionIndex);
        } else {
            checkList = new ArrayList<>();
            pointMapping.put(currentGroupQuestionIndex, checkList);
        }
        if (checkList != null) {
            if (answer != null && answer.equals(question.getCorrectAnswer())) {
                addCorrectAnswer(checkList);
            } else {
                addWrongAnswer(checkList);
            }
        }
    }

    private void addCorrectAnswer(List<Boolean> checkList) {
        checkList.add(true);
        totalCorrectAnswer++;
    }


    private void addWrongAnswer(List<Boolean> checkList) {
        totalWrongAnswer++;
        checkList.add(false);
    }

    @Override
    public GroupQuestion getCurrentGroupQuestionIndex() {
        return groupQuestions.get(currentGroupQuestionIndex);
    }

    protected void setSubmitted() {
        submitted = true;
    }

}
