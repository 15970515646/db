package com.example.demo.VOclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyReportVO {
    private String healthyStatus;
    private String location;
    private String timeStamp;
}
