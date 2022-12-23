package com.example.demo.mapper;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends  JpaRepository<Department, Long>{

    Department findDepartmentByDeptName(String deptName);
}
