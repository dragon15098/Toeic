package com.example.toeic.data.network;

import com.example.toeic.data.network.api.ExamService;
import com.example.toeic.data.network.api.GroupQuestionService;
import com.example.toeic.data.network.api.QuestionService;
import com.example.toeic.data.network.api.UserService;

public class Service {
    public static RxSingleSchedulers getRxSingleSchedulers() {
        return RxSingleSchedulers.DEFAULT;
    }

    public static UserService callUserService() {
        return HttpHelper.getClient().create(UserService.class);
    }

    public static ExamService callExamService() {
        return HttpHelper.getClient().create(ExamService.class);
    }

    public static QuestionService callQuestionService() {
        return HttpHelper.getClient().create(QuestionService.class);
    }

    public static GroupQuestionService callGroupQuestionService() {
        return HttpHelper.getClient().create(GroupQuestionService.class);
    }
}
