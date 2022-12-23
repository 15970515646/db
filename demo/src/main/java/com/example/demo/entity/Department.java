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
@Table(name = "Department")
public class Department {
    @Id
    @Column(name = "deptName", length = 256, nullable = false)
    private String  deptName;

    @OneToMany(targetEntity = Class.class,mappedBy = "department")
    private Set<Class> classSet=new HashSet<>();

    @OneToMany(targetEntity = DeptAdmin.class,mappedBy = "department")
    private Set<DeptAdmin> DeptAdminSet=new HashSet<>();
}
