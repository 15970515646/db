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
    @Column(name = "Dept_name", length = 256, nullable = false)
    private String  dept_name;

    @OneToMany(targetEntity = Class.class,mappedBy = "department")
    private Set<Class> classSet=new HashSet<>();

    @OneToMany(targetEntity = DeptAdmin.class,mappedBy = "department")
    private Set<DeptAdmin> DeptAdminSet=new HashSet<>();
}
