package com.example.toeic.service;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.Question;

import java.util.List;

public interface ExamService {
    List<Exam> findAll();

    List<Integer> getAllCorrectAnswer(Integer id);
}
