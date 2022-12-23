package com.example.demo.mapper;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends JpaRepository<Student,Long> {
    Student findById(String studentId);

    List<Student>findAllByAClass_Class_name(String className);

    List<Student>findAllByStatus(String status);
}
