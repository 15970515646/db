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
    @Column(name = "id", length = 11, nullable = false)
    private Integer id;

    @Column(name = "name",length = 256,nullable = false)
    private String name;

    @Column(name = "phone_number",length = 11,nullable = false)
    private String phone_number;

    @Column(name = "email",length = 256,nullable = false)
    private String email;

    @Column(name = "dormitory",length = 256,nullable = false)
    private String dormitory;

    @Column(name = "home_address",length = 256,nullable = false)
    private String home_address;

    @Column(name = "identity_type",length = 256,nullable = false)
    private String identity_type;

    @Column(name = "identity_id",length = 256,nullable = false)
    private String identity_id;

    @Column(name = "status",length = 256,nullable = false)
    private String status;

    @OneToMany(targetEntity = Daily_report.class,mappedBy = "student")
    private Set<Daily_report> Daily_reportSet=new HashSet<>();

    @OneToMany(targetEntity = LeaveApplication.class,mappedBy = "student")
    private Set<LeaveApplication> LeaveApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = ReturnApplication.class,mappedBy = "student")
    private Set<LeaveApplication> ReturnApplicationSet=new HashSet<>();

    @OneToMany(targetEntity = Log.class,mappedBy = "student")
    private Set<Log> LogSet=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "campus_name")
    private Campus campus;

    @ManyToOne
    @JoinColumn(name = "class_name")
    private Class aClass;

}
