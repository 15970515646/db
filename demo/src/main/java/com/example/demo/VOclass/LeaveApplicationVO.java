package com.example.demo.VOclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveApplicationVO {
    private String id;
    private String reason;
    private String destination;
    private String predictReturnDate;
    private String predictLeaveDate;
    private String status;
    private String createTime;
    private String studentId;

    public LeaveApplicationVO(String reason, String destination, String predictReturnDate, String predictLeaveDate, String status, String createTime, String studentId) {
        this.reason = reason;
        this.destination = destination;
        this.predictReturnDate = predictReturnDate;
        this.predictLeaveDate = predictLeaveDate;
        this.status = status;
        this.createTime = createTime;
        this.studentId = studentId;
    }
}
