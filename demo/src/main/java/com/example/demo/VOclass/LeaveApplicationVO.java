package com.example.demo.VOclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveApplicationVO {
    private String reason;
    private String destination;
    private String predictReturnDate;
    private String predictLeaveDate;
    private String status;
    private String createTime;
}
