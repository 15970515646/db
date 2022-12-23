package com.example.demo.VOclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnApplicationVO {
    private String reason;
    private String location;
    private String predictReturnDate;
    private String status;
    private String createTime;
    private String studentId;
}
