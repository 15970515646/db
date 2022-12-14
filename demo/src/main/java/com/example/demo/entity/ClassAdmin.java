package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ClassAdmin")
public class ClassAdmin {
    @Id
    @Column(name = "teacherId", length = 11, nullable = false)
    private String teacherId;

    @ManyToOne
    @JoinColumn(name = "className")
    private Class aClass;

    @OneToMany(targetEntity = ClassAdminExamineLeave.class,mappedBy = "classAdmin")
    private Set<ClassAdminExamineLeave> classAdminExamineLeaveSet=new HashSet<>();

    @OneToMany(targetEntity = ClassAdminExamineReturn.class,mappedBy = "classAdmin")
    private Set<ClassAdminExamineReturn> classAdminExamineReturnSet=new HashSet<>();
}
