package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.VOclass.DailyReportVO;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ConstVariables;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {
    LeaveApplicationMapper leaveApplicationMapper;
    ReturnApplicationMapper returnApplicationMapper;
    StudentMapper studentMapper;
    StudentLogMapper studentLogMapper;
    DailyReportMapper dailyReportMapper;

    @Autowired
    public StudentServiceimpl(LeaveApplicationMapper leaveApplicationMapper, ReturnApplicationMapper returnApplicationMapper, StudentMapper studentMapper, StudentLogMapper studentLogMapper, DailyReportMapper dailyReportMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.returnApplicationMapper = returnApplicationMapper;
        this.studentMapper = studentMapper;
        this.studentLogMapper = studentLogMapper;
        this.dailyReportMapper = dailyReportMapper;
    }

    @Override
    public Response<?> addDailyReport(String studentId, String location, String healthyStatus) {

        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false,"学生不存在");
        DailyReport dailyReport = new DailyReport(healthyStatus, location, student);

        dailyReportMapper.save(dailyReport);
        return new Response<>(true,"填报成功");
    }

    @Override
    public Response<?> getDailyReport(String studentId, int day_num) {
        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false,"学生不存在");
        List<DailyReport> dailyReports = dailyReportMapper.findByStudent(student);
        List<DailyReportVO> list = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;
//        System.out.println(timestamp.getTime()/ ConstVariables.MILLI_TO_DAY);
        for (DailyReport dailyReport : dailyReports){
            if ((today - dailyReport.getCreateTime().getTime()/ConstVariables.MILLI_TO_DAY)<day_num+1){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(dailyReport.getCreateTime());
                list.add(new DailyReportVO(dailyReport.getHealthy_status(),dailyReport.getLocation(),timeStr));
            }
        }
        return new Response<>(true,"获取成功",list);
    }

    @Override
    public Response<?> getStudentPermission(String studentId) {
        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false, "不存在该学生");
        JSONObject result = new JSONObject();
        result.put("status", student.getStatus());
        return new Response<>(true, "获取学生权限成功", result);
    }

    @Override
    public Response<?> submitStudentLeaveApplication(String reason, String destination, String predictReturnDate, String predictLeaveDate, String status, String studentId) {
        Student student = studentMapper.findById(studentId);
        leaveApplicationMapper.save(new LeaveApplication(reason, destination, predictReturnDate, predictLeaveDate, status, student));
        return new Response<>(true,"提交离校申请成功");
    }

    @Override
    public Response<?> submitStudentReturnApplication(String reason, String location, String predictReturnDate, String status, String studentId) {
        Student student = studentMapper.findById(studentId);
        returnApplicationMapper.save(new ReturnApplication(reason, location, predictReturnDate, status, student));
        return new Response<>(true,"提交返校申请成功");
    }

    @Override
    public Response<?> getStudentLeaveApplication(String studentId, String applicationStatus) {
        return null;
    }

    @Override
    public Response<?> getStudentReturnApplication(String studentId, String applicationStatus) {
        return null;
    }

    @Override
    public Response<?> getTotalLeaveTime(String studentId) {
        return null;
    }


}
