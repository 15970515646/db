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
@Table(name = "Log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Log {
    @Id
    @GeneratedValue(generator = "jpa=uuid", strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 35, nullable = false)
    private Integer id;

    @Column(name = "action", length = 256, nullable = false)
    private String  action;

    @Column(name = "time", length = 256, nullable = false)
    private String  time;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;
}
