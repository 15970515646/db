package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Class")
public class Class {
    @Id
    @Column(name = "className", length = 256, nullable = false)
    private String  className;

    @ManyToOne
    @JoinColumn(name = "deptName")
    private Department department;

    @OneToMany(targetEntity = Student.class,mappedBy = "aClass")
    private Set<Student> StudentSet=new HashSet<>();

    @OneToMany(targetEntity = ClassAdmin.class,mappedBy = "aClass")
    private Set<ClassAdmin> classAdminSet=new HashSet<>();
}
