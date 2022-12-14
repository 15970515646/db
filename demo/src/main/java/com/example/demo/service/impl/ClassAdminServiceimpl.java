package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.VOclass.LeaveApplicationVO;
import com.example.demo.VOclass.ReturnApplicationVO;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import com.example.demo.mapper.*;
import com.example.demo.service.ClassAdminService;
import com.example.demo.utils.ConstVariables;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassAdminServiceimpl implements ClassAdminService {

    LeaveApplicationMapper leaveApplicationMapper;
    ReturnApplicationMapper returnApplicationMapper;
    StudentMapper studentMapper;
    StudentLogMapper studentLogMapper;
    DailyReportMapper dailyReportMapper;
    ClassAdminExamineReturnMapper classAdminExamineReturnMapper;
    ClassAdminExamineLeaveMapper classAdminExamineLeaveMapper;
    ClassAdminMapper classAdminMapper;
    ClassMapper classMapper;

    @Autowired
    public ClassAdminServiceimpl(LeaveApplicationMapper leaveApplicationMapper, ReturnApplicationMapper returnApplicationMapper, StudentMapper studentMapper, StudentLogMapper studentLogMapper, DailyReportMapper dailyReportMapper, ClassAdminExamineReturnMapper classAdminExamineReturnMapper, ClassAdminExamineLeaveMapper classAdminExamineLeaveMapper, ClassAdminMapper classAdminMapper,ClassMapper classMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.returnApplicationMapper = returnApplicationMapper;
        this.studentMapper = studentMapper;
        this.studentLogMapper = studentLogMapper;
        this.dailyReportMapper = dailyReportMapper;
        this.classAdminExamineReturnMapper = classAdminExamineReturnMapper;
        this.classAdminExamineLeaveMapper = classAdminExamineLeaveMapper;
        this.classAdminMapper = classAdminMapper;
        this.classMapper = classMapper;
    }

    @Override
    public Response<?> getStudentApplication(String teacherId) {
        ClassAdmin classAdmin = classAdminMapper.findByTeacherId(teacherId);
        Class aClass = classAdmin.getAClass();
        List<Student> studentList = studentMapper.findByAclass(aClass);
        if (studentList.size()==0){
            return new Response<>(false,"????????????????????????");
        }
        List<ReturnApplicationVO> returnApplications = new ArrayList<>();
        List<LeaveApplicationVO> leaveApplications = new ArrayList<>();
        for(Student student : studentList){
            for(LeaveApplication leaveApplication : leaveApplicationMapper.findByStudentAndStatus(student, ConstVariables.CLASS_ADMIN_CHECK)){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(leaveApplication.getCreateTime());
                leaveApplications.add(new LeaveApplicationVO(leaveApplication.getId(),leaveApplication.getReason(), leaveApplication.getDestination(), leaveApplication.getPredictReturnDate(),leaveApplication.getPredictLeaveDate(),leaveApplication.getStatus(),timeStr,leaveApplication.getStudent().getId()));
            }
            for(ReturnApplication returnApplication : returnApplicationMapper.findByStudentAndStatus(student, ConstVariables.CLASS_ADMIN_CHECK)){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(returnApplication.getCreateTime());
                returnApplications.add(new ReturnApplicationVO(returnApplication.getId(),returnApplication.getReason(), returnApplication.getLocation(), returnApplication.getPredictReturnDate(),returnApplication.getStatus(),timeStr,returnApplication.getStudent().getId()));
            }
        }
        JSONObject res = new JSONObject();
        res.put("????????????",leaveApplications);
        res.put("????????????", returnApplications);
        return new Response<>(true,"????????????????????????",res);
    }

    @Override
    public Response<?> examineLeave(String applicationId, String operation) {
        LeaveApplication leaveApplication = leaveApplicationMapper.findById(applicationId);
        if (leaveApplication==null)
            return new Response<>(false,"??????ID??????");
        if (!leaveApplication.getStatus().equals(ConstVariables.CLASS_ADMIN_CHECK))
            return new Response<>(false,"???????????????????????????????????????");
        if (operation.equals(ConstVariables.APPROVE_APPLY)){
            leaveApplication.setStatus(ConstVariables.DEPT_ADMIN_CHECK);
            leaveApplicationMapper.save(leaveApplication);
        }
        else {
            leaveApplication.setStatus(ConstVariables.APPLICATION_REFUSED);
            leaveApplicationMapper.save(leaveApplication);
        }
        return new Response<>(true,"?????????????????????????????????");
    }

    @Override
    public Response<?> examineReturn(String applicationId, String operation) {
        ReturnApplication returnApplication = returnApplicationMapper.findById(applicationId);
        if(returnApplication==null)
            return new Response<>(false,"??????ID??????");
        if (!returnApplication.getStatus().equals(ConstVariables.CLASS_ADMIN_CHECK))
            return new Response<>(false,"???????????????????????????????????????");
        if (operation.equals(ConstVariables.APPROVE_APPLY)){
            returnApplication.setStatus(ConstVariables.DEPT_ADMIN_CHECK);
            returnApplicationMapper.save(returnApplication);
        }
        else {
            returnApplication.setStatus(ConstVariables.APPLICATION_REFUSED);
            returnApplicationMapper.save(returnApplication);
        }
        return new Response<>(true,"?????????????????????????????????");
    }


}
