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
    @Column(name = "student_id", length = 11, nullable = false)
    private String id;

    @Column(name = "name",length = 256,nullable = false)
    private String name;

    @Column(name = "phone_number",length = 11,nullable = false)
    private String phoneNumber;

    @Column(name = "email",length = 256,nullable = false)
    private String email;

    @Column(name = "dormitory",length = 256,nullable = false)
    private String dormitory;

    @Column(name = "home_address",length = 256,nullable = false)
    private String homeAddress;

    @Column(name = "identity_type",length = 256,nullable = false)
    private String identityType;

    @Column(name = "identity_id",length = 256,nullable = false)
    private String identityId;

    @Column(name = "status",length = 256,nullable = false)
    private String status;

    @OneToMany(targetEntity = DailyReport.class,mappedBy = "student")
    private Set<DailyReport> dailyReportSet=new HashSet<>();

    @OneToMany(targetEntity = LeaveApplication.class,mappedBy = "student")
    private Set<LeaveApplication> LeaveApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = ReturnApplication.class,mappedBy = "student")
    private Set<LeaveApplication> ReturnApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = StudentLog.class,mappedBy = "student")
    private Set<StudentLog> LogSet=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campus_name")
    private Campus campus;

    @ManyToOne
    @JoinColumn(name = "class_name")
    private Class aClass;

}
