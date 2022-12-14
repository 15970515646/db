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
@Table(name = "DeptAdminExamineReturn")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DeptAdminExamineReturn{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "reason", length = 256, nullable = false)
    private String reason;

    public DeptAdminExamineReturn(String reason, String location, String predictReturnDate, String status, Student student, DeptAdmin deptAdmin) {
        this.reason = reason;
        this.location = location;
        this.predictReturnDate = predictReturnDate;
        this.status = status;
        this.student = student;
        this.deptAdmin = deptAdmin;
    }

    @Column(name = "location", length = 256, nullable = false)
    private String location;

    @Column(name = "predictReturnDate", length = 256, nullable = false)
    private String predictReturnDate;

    @Column(name = "status", length = 256, nullable = false)
    private String status;

    @Column(name = "createTime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private DeptAdmin deptAdmin;

}

