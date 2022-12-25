package com.example.demo.mapper;

import com.example.demo.entity.ClassAdminExamineLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassAdminExamineLeaveMapper extends JpaRepository<ClassAdminExamineLeave, Long> {
    
}
