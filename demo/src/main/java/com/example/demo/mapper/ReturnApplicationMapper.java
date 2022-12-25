package com.example.demo.mapper;

import com.example.demo.entity.ReturnApplication;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnApplicationMapper extends JpaRepository<ReturnApplication, Long> {
    List<ReturnApplication> findByStudentAndStatus(Student student, String status);
    ReturnApplication findById(String id);
    List <ReturnApplication>findAllByStatus(String status);
}
