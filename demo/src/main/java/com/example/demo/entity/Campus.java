package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Campus")
public class Campus {
    @Id
    @Column(name = "campus_name", length = 256, nullable = false)
    private String  campus_name;

    @Column(name = "campus_status", length = 256, nullable = false)
    private String  campus_status;

    @OneToMany(targetEntity = Student.class,mappedBy = "campus")
    private Set<Student> StudentSet=new HashSet<>();
}
