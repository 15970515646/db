package com.example.demo.mapper;

import com.example.demo.entity.DeptAdminExamineLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptAdminExamineLeaveMapper extends JpaRepository<DeptAdminExamineLeave,Long> {

}
