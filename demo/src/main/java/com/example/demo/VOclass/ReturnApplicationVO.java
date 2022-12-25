package com.example.demo.VOclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnApplicationVO {
    private String id;
    private String reason;
    private String location;
    private String predictReturnDate;
    private String status;
    private String createTime;
    private String studentId;

    public ReturnApplicationVO(String reason, String location, String predictReturnDate, String status, String createTime, String studentId) {
        this.reason = reason;
        this.location = location;
        this.predictReturnDate = predictReturnDate;
        this.status = status;
        this.createTime = createTime;
        this.studentId = studentId;
    }
}
