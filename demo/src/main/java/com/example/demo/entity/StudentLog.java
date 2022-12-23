package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StudentLog")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class StudentLog {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "action", length = 256, nullable = false)
    private String  action;

    @Column(name = "campusName", length = 256, nullable = false)
    private String  campusName;

    @Column(name = "createTime",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    public StudentLog(String action, String campusName, Student student) {
        this.action = action;
        this.campusName = campusName;
        this.student = student;
    }
}
