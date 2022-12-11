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
@Table(name = "Return_application")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ReturnApplication {
    @Id
    @GeneratedValue(generator = "jpa=uuid", strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 35, nullable = false)
    private Integer id;

    @Column(name = "reason", length = 256, nullable = false)
    private String  reason;

    @Column(name = "location", length = 256, nullable = false)
    private String  location;

    @Column(name = "predict_return_date", length = 256, nullable = false)
    private String  predict_return_date;

    @Column(name = "status", length = 256, nullable = false)
    private String  status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;
}
