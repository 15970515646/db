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
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "studentId", length = 11, nullable = false)
    private String id;

    @Column(name = "name",length = 256,nullable = false)
    private String name;

    @Column(name = "phoneNumber",length = 11,nullable = false)
    private String phoneNumber;

    @Column(name = "email",length = 256,nullable = false)
    private String email;

    @Column(name = "dormitory",length = 256,nullable = false)
    private String dormitory;

    @Column(name = "homeAddress",length = 256,nullable = false)
    private String homeAddress;

    @Column(name = "identityType",length = 256,nullable = false)
    private String identityType;

    @Column(name = "identityId",length = 256,nullable = false)
    private String identityId;

    @Column(name = "status",length = 256,nullable = false)
    private String status;

    @OneToMany(targetEntity = DailyReport.class,mappedBy = "student")
    private Set<DailyReport> dailyReportSet=new HashSet<>();

    @OneToMany(targetEntity = LeaveApplication.class,mappedBy = "student")
    private Set<LeaveApplication> LeaveApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = ReturnApplication.class,mappedBy = "student")
    private Set<ReturnApplication> ReturnApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = StudentLog.class,mappedBy = "student")
    private Set<StudentLog> LogSet=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campusName")
    private Campus campus;

    @ManyToOne
    @JoinColumn(name = "className")
    private Class aClass;

    @OneToMany(targetEntity = ClassAdminExamineReturn.class,mappedBy = "student")
    private Set<ClassAdminExamineReturn> classAdminExamineReturnSet=new HashSet<>();

    @OneToMany(targetEntity = DeptAdminExamineReturn.class,mappedBy = "student")
    private Set<DeptAdminExamineReturn> deptAdminExamineReturnSet=new HashSet<>();

    @OneToMany(targetEntity = ClassAdminExamineLeave.class,mappedBy = "student")
    private Set<ClassAdminExamineLeave> classAdminExamineLeaveSet=new HashSet<>();

    @OneToMany(targetEntity = DeptAdminExamineLeave.class,mappedBy = "student")
    private Set<DeptAdminExamineLeave> deptAdminExamineLeaveSet=new HashSet<>();
}
