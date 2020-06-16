package com.example.toeic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.toeic.model.exam.Part;

public interface PartRepository extends JpaRepository<Part, Integer>, JpaSpecificationExecutor<Part> {

}