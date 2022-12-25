package com.example.demo.mapper;

import com.example.demo.entity.DeptAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptAdminMapper extends JpaRepository<DeptAdmin, Long> {
    public DeptAdmin findByTeacherId(String teacherId);
}
