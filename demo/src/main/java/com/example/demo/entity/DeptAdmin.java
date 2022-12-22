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
@Table(name = "DeptAdmin")
public class DeptAdmin {
    @Id
    @Column(name = "teacher_id", length = 11, nullable = false)
    private String teacher_id;

    @ManyToOne
    @JoinColumn(name = "dept_name")
    private Department department;

    @OneToMany(targetEntity = DeptAdminExamineReturn.class,mappedBy = "deptAdmin")
    private Set<DeptAdminExamineReturn> deptAdminExamineReturnSet=new HashSet<>();

    @OneToMany(targetEntity = DeptAdminExamineLeave.class,mappedBy = "deptAdmin")
    private Set<DeptAdminExamineLeave> deptAdminExamineLeaveSet=new HashSet<>();

}