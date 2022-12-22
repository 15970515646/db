package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DeptAdminExamineLeave")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DeptAdminExamineLeave {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "reason", length = 256, nullable = false)
    private String  reason;

    @Column(name = "destination", length = 256, nullable = false)
    private String  destination;

    @Column(name = "predict_return_date", length = 256, nullable = false)
    private String  predict_return_date;

    @Column(name = "predict_leave_date", length = 256, nullable = false)
    private String  predict_leave_date;

    @Column(name = "status", length = 256, nullable = false)
    private String  status;

    @Column(name = "create_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private DeptAdmin deptAdmin;
}