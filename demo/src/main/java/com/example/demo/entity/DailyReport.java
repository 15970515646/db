package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DailyReport")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DailyReport {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "healthy_status", length = 35, nullable = false)
    private String  healthy_status;

    @Column(name = "location", length = 35, nullable = false)
    private String  location;

    @Column(name = "create_time",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;

    public DailyReport(String healthy_status, String location, Student student) {
        this.healthy_status = healthy_status;
        this.location = location;
        this.student = student;
    }
}
