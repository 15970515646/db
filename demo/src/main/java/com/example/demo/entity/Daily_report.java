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
@Table(name = "Daily_report")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Daily_report {
    @Id
    @GeneratedValue(generator = "jpa=uuid", strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 35, nullable = false)
    private Integer id;

    @Column(name = "healthy_status", length = 35, nullable = false)
    private String  healthy_status;

    @Column(name = "location", length = 35, nullable = false)
    private String  location;

    @Column(name = "date", length = 35, nullable = false)
    private String  date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student  student;
}
