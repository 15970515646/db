package com.example.demo.entity;

import com.example.demo.utils.ConstVariables;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Return_application")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ReturnApplication {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "reason", length = 256, nullable = false)
    private String  reason;

    @Column(name = "location", length = 256, nullable = false)
    private String  location;

    @Column(name = "predictReturnDate", length = 256, nullable = false)
    private String  predictReturnDate;

    @Column(name = "status", length = 256, nullable = false)
    private String  status;

    @Column(name = "createTime",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;


    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student  student;

    public ReturnApplication(String reason, String location, String predict_return_date, Student student) {
        this.reason = reason;
        this.location = location;
        this.predictReturnDate = predict_return_date;
        this.status = ConstVariables.CLASS_ADMIN_CHECK;
        this.student = student;
    }
}
