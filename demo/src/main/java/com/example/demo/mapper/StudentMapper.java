package com.example.demo.mapper;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends JpaRepository<Student,Long> {
    Student findById(String studentId);
}
