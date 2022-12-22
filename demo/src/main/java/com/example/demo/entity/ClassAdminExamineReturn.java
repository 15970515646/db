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
@Table(name = "ClassAdminExamineReturn")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ClassAdminExamineReturn{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "reason", length = 256, nullable = false)
    private String reason;

    @Column(name = "location", length = 256, nullable = false)
    private String location;

    @Column(name = "predict_return_date", length = 256, nullable = false)
    private String predict_return_date;

    @Column(name = "status", length = 256, nullable = false)
    private String status;

    @Column(name = "create_time", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private ClassAdmin classAdmin;
}