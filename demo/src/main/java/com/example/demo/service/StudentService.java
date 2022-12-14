package com.example.demo.service;

import com.example.demo.utils.Response;

public interface StudentService {
    public Response<?> addDailyReport(String studentId, String location, String healthyStatus);
    public Response<?> getDailyReport(String studentId, int day_num);
    public Response<?> getStudentPermission(String studentId);
    public Response<?> submitStudentLeaveApplication(String reason, String destination, String predictReturnDate, String predictLeaveDate, String status, String studentId);
    public Response<?> submitStudentReturnApplication(String reason, String location, String predictReturnDate,String status, String studentId);
    public Response<?> getStudentLeaveApplication(String studentId, String applicationStatus);
    public Response<?> getStudentReturnApplication(String studentId, String applicationStatus);
    public Response<?> getTotalLeaveTime(String studentId);

}
