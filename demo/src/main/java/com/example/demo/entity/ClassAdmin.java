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
@Table(name = "ClassAdmin")
public class ClassAdmin {
    @Id
    @Column(name = "teacher_id", length = 11, nullable = false)
    private Integer teacher_id;

}
