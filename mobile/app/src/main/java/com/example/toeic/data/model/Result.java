package com.example.toeic.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Result implements Serializable {

    QuestionPoint partOneScore;
    QuestionPoint partTwoScore;
    GroupQuestionPoint partThreeScore;
    GroupQuestionPoint partFourScore;

    QuestionPoint partFiveScore;
    GroupQuestionPoint partSixScore;
    GroupQuestionPoint partSevenScore;

    public int calculatePointsListening() {
        int listeningPoint = partOneScore.getTotalCorrectAnswer() + partTwoScore.getTotalCorrectAnswer()
                + partThreeScore.getTotalCorrectAnswer() + partFourScore.getTotalCorrectAnswer();
        int score = 5;
        if (listeningPoint > 96) {
            return 495;
        } else if (listeningPoint > 75) {
            return (listeningPoint - 2) * 5 + 25;
        } else if (listeningPoint > 1) {
            return (listeningPoint - 1) * 5 + 15;
        }
        return score;
    }

    public void calculatePointsReading() {

    }

    public Integer getCorrectQuestionByPart(Integer part) {
        switch (part) {
            case 1:
                return partOneScore.getTotalCorrectAnswer();
            case 2:
                return partTwoScore.getTotalCorrectAnswer();
            case 3:
                return partThreeScore.getTotalCorrectAnswer();
            case 4:
                return partFourScore.getTotalCorrectAnswer();
            case 5:
                return partFiveScore.getTotalCorrectAnswer();
            case 6:
                return partSixScore.getTotalCorrectAnswer();
            case 7:
                return partSevenScore.getTotalCorrectAnswer();
        }
        return 0;
    }
}
