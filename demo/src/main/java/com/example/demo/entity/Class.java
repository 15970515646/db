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
    @Column(name = "class_name", length = 256, nullable = false)
    private String  class_name;

    @ManyToOne
    @JoinColumn(name = "dept_name")
    private Department department;

    @OneToMany(targetEntity = Student.class,mappedBy = "aClass")
    private Set<Student> StudentSet=new HashSet<>();
}
