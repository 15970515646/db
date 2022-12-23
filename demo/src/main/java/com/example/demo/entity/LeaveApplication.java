package com.example.demo.entity;

import com.example.demo.utils.ConstVariables;
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

    @Column(name = "predictReturnDate", length = 256, nullable = false)
    private String  predictReturnDate;

    @Column(name = "predictLeaveDate", length = 256, nullable = false)
    private String  predictLeaveDate;

    @Column(name = "status", length = 256, nullable = false)
    private String  status;

    @Column(name = "createTime",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;


    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student  student;

    public LeaveApplication(String reason, String destination, String predict_return_date, String predict_leave_date, Student student) {
        this.reason = reason;
        this.destination = destination;
        this.predictReturnDate = predict_return_date;
        this.predictLeaveDate = predict_leave_date;
        this.status = ConstVariables.CLASS_ADMIN_CHECK;
        this.student = student;
    }
}

