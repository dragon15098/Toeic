package com.example.toeic.service.impl;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Part;
import com.example.toeic.model.exam.Question;
import com.example.toeic.repository.*;
import com.example.toeic.service.GroupQuestionService;
import com.example.toeic.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GroupQuestionServiceImpl implements GroupQuestionService {
    private final GroupQuestionRepository groupQuestionRepository;
    @Override
    public List<GroupQuestion> findByPartAndExams(Integer partId, Integer examId) {
        return groupQuestionRepository.findAllByPartIdAndExamId(partId, examId);
    }
}
