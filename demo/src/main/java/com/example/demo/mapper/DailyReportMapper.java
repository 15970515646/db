package com.example.demo.mapper;

import com.example.demo.entity.DailyReport;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyReportMapper extends JpaRepository<DailyReport, Long> {
    public List<DailyReport> findByStudent(Student student);
}
