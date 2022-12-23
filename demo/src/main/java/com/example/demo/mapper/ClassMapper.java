package com.example.demo.mapper;

import com.example.demo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassMapper extends JpaRepository<Class,Long> {

    Class findClassByClass_name(String className);
}
