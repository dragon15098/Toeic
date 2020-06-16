package com.example.toeic.service.impl;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.Question;
import com.example.toeic.repository.ExamRepository;
import com.example.toeic.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }
}
