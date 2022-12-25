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
@Table(name = "ClassAdminExamineReturn")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ClassAdminExamineReturn{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 35, nullable = false)
    private String id;

    @Column(name = "reason", length = 256, nullable = false)
    private String reason;

    @Column(name = "location", length = 256, nullable = false)
    private String location;

    @Column(name = "predictReturnDate", length = 256, nullable = false)
    private String predict_return_date;

    @Column(name = "status", length = 256, nullable = false)
    private String status;

    @Column(name = "createTime", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    public ClassAdminExamineReturn(String reason, String location, String predict_return_date,  Student student, ClassAdmin classAdmin) {
        this.reason = reason;
        this.location = location;
        this.predict_return_date = predict_return_date;
        this.status = ConstVariables.CLASS_ADMIN_CHECK;
        this.student = student;
        this.classAdmin = classAdmin;
    }

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private ClassAdmin classAdmin;
}