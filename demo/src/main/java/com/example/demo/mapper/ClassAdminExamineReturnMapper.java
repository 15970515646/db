package com.example.demo.mapper;


import com.example.demo.entity.ClassAdminExamineReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassAdminExamineReturnMapper extends JpaRepository<ClassAdminExamineReturn, Long> {

}
