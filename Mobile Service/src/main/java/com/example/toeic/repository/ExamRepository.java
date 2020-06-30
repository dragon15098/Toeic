package com.example.toeic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.toeic.model.exam.Exam;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer>, JpaSpecificationExecutor<Exam> {
}
