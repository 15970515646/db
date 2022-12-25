package com.example.demo.mapper;


import com.example.demo.entity.DeptAdminExamineReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptAdminExamineReturnMapper extends JpaRepository<DeptAdminExamineReturn, Long> {
}
