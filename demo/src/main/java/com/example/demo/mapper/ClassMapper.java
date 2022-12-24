package com.example.demo.mapper;

import com.example.demo.entity.Class;
import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassMapper extends JpaRepository<Class,Long> {

     public Class findByClassName(String className);
     public List<Class> findByDepartment(Department department);
}
