package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.VOclass.DailyReportVO;
import com.example.demo.VOclass.LeaveApplicationVO;
import com.example.demo.VOclass.ReturnApplicationVO;
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
    ClassAdminExamineReturnMapper classAdminExamineReturnMapper;
    ClassAdminExamineLeaveMapper classAdminExamineLeaveMapper;
    ClassAdminMapper classAdminMapper;

    @Autowired
    public StudentServiceimpl(LeaveApplicationMapper leaveApplicationMapper, ReturnApplicationMapper returnApplicationMapper, StudentMapper studentMapper, StudentLogMapper studentLogMapper, DailyReportMapper dailyReportMapper, ClassAdminExamineReturnMapper classAdminExamineReturnMapper, ClassAdminExamineLeaveMapper classAdminExamineLeaveMapper, ClassAdminMapper classAdminMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.returnApplicationMapper = returnApplicationMapper;
        this.studentMapper = studentMapper;
        this.studentLogMapper = studentLogMapper;
        this.dailyReportMapper = dailyReportMapper;
        this.classAdminExamineReturnMapper = classAdminExamineReturnMapper;
        this.classAdminExamineLeaveMapper = classAdminExamineLeaveMapper;
        this.classAdminMapper = classAdminMapper;

    }



    @Override
    public Response<?> addDailyReport(String studentId, String location, String healthyStatus) {

        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false,"???????????????");
        DailyReport dailyReport = new DailyReport(healthyStatus, location, student);

        dailyReportMapper.save(dailyReport);
        return new Response<>(true,"????????????");
    }

    @Override
    public Response<?> getDailyReport(String studentId, int day_num) {
        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false,"???????????????");
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
        return new Response<>(true,"????????????",list);
    }

    @Override
    public Response<?> getStudentPermission(String studentId) {
        Student student = studentMapper.findById(studentId);
        if (student == null)
            return new Response<>(false, "??????????????????");
        JSONObject result = new JSONObject();
        result.put("status", student.getStatus());
        return new Response<>(true, "????????????????????????", result);
    }

    @Override
    public Response<?> submitStudentLeaveApplication(String reason, String destination, String predictReturnDate, String predictLeaveDate, String studentId) {
        Student student = studentMapper.findById(studentId);
//        List<ClassAdmin> classAdmins= classAdminMapper.findByAClass(student.getAclass());
//        if(classAdmins.size()==0)
//            return new Response<>(false,"?????????????????????????????????????????????????????????");
//        ClassAdmin classAdmin = classAdmins.get(0);
//        classAdminExamineLeaveMapper.save(new ClassAdminExamineLeave(reason, destination, predictReturnDate, predictLeaveDate, student, classAdmin))
        leaveApplicationMapper.save(new LeaveApplication(reason, destination, predictReturnDate, predictLeaveDate, student));

        return new Response<>(true,"????????????????????????");
    }

    @Override
    public Response<?> submitStudentReturnApplication(String reason, String location, String predictReturnDate, String studentId) {
        Student student = studentMapper.findById(studentId);
//        List<ClassAdmin> classAdmins= classAdminMapper.findByAClass(student.getAclass());
//        if(classAdmins.size()==0)
//            return new Response<>(false,"?????????????????????????????????????????????????????????");
//        ClassAdmin classAdmin = classAdmins.get(0);
//        classAdminExamineReturnMapper.save(new ClassAdminExamineReturn(reason, location, predictReturnDate, student,classAdmin));
        returnApplicationMapper.save(new ReturnApplication(reason, location, predictReturnDate, student));
        return new Response<>(true,"????????????????????????");
    }

    @Override
    public Response<?> getStudentLeaveApplication(String studentId, String applicationStatus) {
        Student student = studentMapper.findById(studentId);
        List<LeaveApplication> list = leaveApplicationMapper.findByStudentAndStatus(student, applicationStatus);
        List<LeaveApplicationVO> res = new ArrayList<>();
        for (LeaveApplication leaveApplication : list){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(leaveApplication.getCreateTime());
            res.add(new LeaveApplicationVO(leaveApplication.getReason(), leaveApplication.getDestination(), leaveApplication.getPredictReturnDate(),leaveApplication.getPredictLeaveDate(),leaveApplication.getStatus(),timeStr,leaveApplication.getStudent().getId()));
        }
        return new Response<>(true,"????????????",res);
    }

    @Override
    public Response<?> getStudentReturnApplication(String studentId, String applicationStatus) {
        Student student = studentMapper.findById(studentId);
        List<ReturnApplication> list = returnApplicationMapper.findByStudentAndStatus(student, applicationStatus);
        List<ReturnApplicationVO> res = new ArrayList<>();
        for (ReturnApplication returnApplication : list){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = df.format(returnApplication.getCreateTime());
            res.add(new ReturnApplicationVO(returnApplication.getReason(), returnApplication.getLocation(), returnApplication.getPredictReturnDate(),returnApplication.getStatus(),timeStr,returnApplication.getStudent().getId()));
        }
        return new Response<>(true,"????????????",res);
    }

    @Override
    public Response<?> getTotalLeaveTime(String studentId) {
        Student student = studentMapper.findById(studentId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;

        if (student == null)
            return new Response<>(false,"??????????????????");
        List<StudentLog> list = studentLogMapper.findByStudent(student);
        int length = list.size();
        if (length == 0)
            return new Response<>(false, "??????????????????????????????");
        int totalHours = 0;
        for (int i=0;i<length-1;i++) {
            if ((today - list.get(i).getCreateTime().getTime() / ConstVariables.MILLI_TO_DAY) < 366) {
                if (list.get(i).getAction().equals(ConstVariables.OUT_CAMPUS)) {
                    totalHours += (list.get(i + 1).getCreateTime().getTime() / ConstVariables.MILLI_TO_HOUR) -
                            (list.get(i).getCreateTime().getTime() / ConstVariables.MILLI_TO_HOUR);
                }
            }
        }
        return new Response<>(true,"????????????", totalHours);
    }


}
