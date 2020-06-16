package com.example.toeic.service;

import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Question;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface GroupQuestionService {
    List<GroupQuestion> findByPartAndExams(Integer partId, Integer examId);
}
