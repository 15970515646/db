package com.example.demo.mapper;


import com.example.demo.entity.LeaveApplication;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationMapper extends JpaRepository<LeaveApplication, Long> {
    List<LeaveApplication>findByStudentAndStatus(Student student, String status);

}
