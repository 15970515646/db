package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "DailyReport")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DailyReport {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "healthyStatus", length = 35, nullable = false)
    private String  healthy_status;

    @Column(name = "location", length = 35, nullable = false)
    private String  location;

    @Column(name = "createTime",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student  student;

    public DailyReport(String healthy_status, String location, Student student) {
        this.healthy_status = healthy_status;
        this.location = location;
        this.student = student;
    }
}
