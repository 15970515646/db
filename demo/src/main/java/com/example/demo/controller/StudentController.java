package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.StudentService;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/getstatus/{studentId}")
    public Response<?> getStudentStatus(@PathVariable(name = "studentId")String studentId){
        return studentService.getStudentPermission(studentId);
    }
    @PostMapping("/dailyreport")
    public Response<?> submitDailyReport(@RequestBody JSONObject body){
        String healthyStatus = (String) body.get("healthyStatus");
        String location = (String) body.get("location");
        String studentId = (String) body.get("studentId");
        return studentService.addDailyReport(studentId,location,healthyStatus);
    }
    @GetMapping("/getdailyreport")
    public Response<?> getDailyReport(@RequestBody JSONObject body){
        String studentId = (String) body.get("studentId");
        int dayNum = (Integer) body.get("dayNum");
        return studentService.getDailyReport(studentId, dayNum);
    }
    @PostMapping("/leaveapply")
    public Response<?> submitLeaveApplication(@RequestBody JSONObject body){
        String reason = (String) body.get("reason");
        String destination = (String) body.get("destination");
        String predictReturnDate = (String) body.get("predictReturnDate");
        String predictLeaveDate = (String) body.get("predictLeaveDate");
        String studentId = (String) body.get("studentId");
        return studentService.submitStudentLeaveApplication(reason, destination, predictReturnDate, predictLeaveDate, studentId);

    }
    @PostMapping("/returnapply")
    public Response<?> submitReturnApplication(@RequestBody JSONObject body){
        String reason = (String) body.get("reason");
        String location = (String) body.get("location");
        String predictReturnDate = (String) body.get("predictReturnDate");
        String studentId = (String) body.get("studentId");
        return studentService.submitStudentReturnApplication(reason, location, predictReturnDate, studentId);
    }
    @GetMapping("/getleaveapplication")
    public Response<?> getLeaveApplication(@RequestBody JSONObject body){
        String studentId = (String) body.get("studentId");
        String status = (String) body.get("status");
        return studentService.getStudentLeaveApplication(studentId, status);
    }
    @GetMapping("/getreturnapplication")
    public Response<?> getReturnApplication(@RequestBody JSONObject body){
        String studentId = (String) body.get("studentId");
        String status = (String) body.get("status");
        return studentService.getStudentReturnApplication(studentId, status);
    }
    @GetMapping("/getleavetime/{studentId}")
    public Response<?> getLeaveTime(@PathVariable(name = "studentId")String studentId){
        return studentService.getTotalLeaveTime(studentId);
    }

    // 查询 n 天的打卡记录



}
