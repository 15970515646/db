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
@Table(name = "StudentLog")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class StudentLog {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "action", length = 256, nullable = false)
    private String  action;

    @Column(name = "time", length = 256, nullable = false)
    private String  time;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentLog(String action, String time, Student student) {
        this.action = action;
        this.time = time;
        this.student = student;
    }
}
