package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Leave_application")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LeaveApplication {
    @Id
    @GeneratedValue(generator = "jpa=uuid", strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 35, nullable = false)
    private Integer id;

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

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;
}

