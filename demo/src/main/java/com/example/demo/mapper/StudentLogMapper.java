package com.example.demo.mapper;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLogMapper extends JpaRepository<StudentLog, Long> {
    List<StudentLog> findByStudent(Student student);

}
