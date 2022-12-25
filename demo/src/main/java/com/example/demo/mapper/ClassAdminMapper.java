package com.example.demo.mapper;


import com.example.demo.entity.Class;
import com.example.demo.entity.ClassAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassAdminMapper extends JpaRepository<ClassAdmin, Long> {

    public ClassAdmin findByTeacherId(String teacherId);
}
