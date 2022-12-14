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
@Table(name = "Leave_application")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LeaveApplication {
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

    public LeaveApplication(String reason, String destination, String predict_return_date, String predict_leave_date, String status, Student student) {
        this.reason = reason;
        this.destination = destination;
        this.predict_return_date = predict_return_date;
        this.predict_leave_date = predict_leave_date;
        this.status = status;
        this.student = student;
    }
}

