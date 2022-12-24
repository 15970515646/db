package com.example.demo.VOclass;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveStudentVO {
    private String id;
    private String name;
    private String leaveTime;
}
