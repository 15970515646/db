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

    @Column(name = "predictReturnDate", length = 256, nullable = false)
    private String  predictReturnDate;

    @Column(name = "predictLeaveDate", length = 256, nullable = false)
    private String  predictLeaveDate;

    @Column(name = "status", length = 256, nullable = false)
    private String  status;

    @Column(name = "createTime",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;


    public DeptAdminExamineLeave(String reason, String destination, String predictReturnDate, String predictLeaveDate, String status, Student student, DeptAdmin deptAdmin) {
        this.reason = reason;
        this.destination = destination;
        this.predictReturnDate = predictReturnDate;
        this.predictLeaveDate = predictLeaveDate;
        this.status = status;
        this.student = student;
        this.deptAdmin = deptAdmin;
    }

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student  student;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private DeptAdmin deptAdmin;
}
