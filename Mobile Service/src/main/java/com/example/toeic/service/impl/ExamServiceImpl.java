package com.example.toeic.service.impl;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Part;
import com.example.toeic.model.exam.Question;
import com.example.toeic.repository.ExamRepository;
import com.example.toeic.repository.GroupQuestionRepository;
import com.example.toeic.repository.QuestionRepository;
import com.example.toeic.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;

    private final GroupQuestionRepository groupQuestionRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public List<Integer> getAllCorrectAnswer(Integer examId) {
        return questionRepository.findAllByExamIdOrderById(examId).stream().map(Question::getCorrectAnswer).collect(Collectors.toList());
    }
}
