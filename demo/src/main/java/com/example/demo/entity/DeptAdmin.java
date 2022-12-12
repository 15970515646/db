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
@Table(name = "DeptAdmin")
public class DeptAdmin {
    @Id
    @Column(name = "teacher_id", length = 11, nullable = false)
    private Integer teacher_id;

}