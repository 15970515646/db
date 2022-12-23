package com.example.demo.service.impl;


import com.example.demo.VOclass.LeaveApplicationVO;
import com.example.demo.VOclass.ReturnApplicationVO;
import com.example.demo.VOclass.StudentVO;
import com.example.demo.entity.Class;
import com.example.demo.entity.Department;
import com.example.demo.entity.LeaveApplication;
import com.example.demo.entity.ReturnApplication;
import com.example.demo.entity.Student;
import com.example.demo.mapper.*;
import com.example.demo.service.DeptAdminService;
import com.example.demo.utils.ConstVariables;
import com.example.demo.utils.Response;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.ConstVariables.MILLI_TO_DAY;

@Service
public class DeptAdminServiceimpl implements DeptAdminService {

    LeaveApplicationMapper leaveApplicationMapper;

    ClassMapper classMapper;
    DepartmentMapper departmentMapper;
    StudentMapper studentMapper;
    ReturnApplicationMapper returnApplicationMapper;

    public   DeptAdminServiceimpl(ClassMapper classMapper,DepartmentMapper departmentMapper, StudentMapper studentMapper, LeaveApplicationMapper leaveApplicationMapper, ReturnApplicationMapper returnApplicationMapper){
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.returnApplicationMapper =  returnApplicationMapper;
        this.studentMapper = studentMapper;
        this.departmentMapper = departmentMapper;
        this.classMapper = classMapper;
    }


    @Override
    public Response<?> getNoApplyForLeave(int day_num) {
        Timestamp time=new Timestamp(System.currentTimeMillis());
        long lasttime = time.getTime() - day_num*MILLI_TO_DAY;
        List<LeaveApplication> leaveList1 = leaveApplicationMapper.findAllByStatus(ConstVariables.CLASS_ADMIN_CHECK);
        List<LeaveApplication> leaveList2 = leaveApplicationMapper.findAllByStatus(ConstVariables.DEPT_ADMIN_CHECK);
        List<LeaveApplicationVO> result = new ArrayList<>();
        for(LeaveApplication l:leaveList1){
            if(l.getCreateTime().getTime()>=lasttime){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(l.getCreateTime());
                result.add(new LeaveApplicationVO(l.getReason(),l.getDestination(),l.getPredictReturnDate(),l.getPredictLeaveDate(),l.getStatus(),timeStr,l.getStudent().getId()));
            }
        }
        for(LeaveApplication l:leaveList2){
            if(l.getCreateTime().getTime()>=lasttime){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(l.getCreateTime());
                result.add(new LeaveApplicationVO(l.getReason(),l.getDestination(),l.getPredictReturnDate(),l.getPredictLeaveDate(),l.getStatus(),timeStr,l.getStudent().getId()));
            }
        }
        return new Response<>(true,"查询成功",result);
    }

    @Override
    public Response<?> getNoApplyForReturn(int day_num) {
        Timestamp time=new Timestamp(System.currentTimeMillis());
        long lasttime = time.getTime() - day_num*MILLI_TO_DAY;
        List<ReturnApplication> returnList1 = returnApplicationMapper.findAllByStatus(ConstVariables.CLASS_ADMIN_CHECK);
        List<ReturnApplication> returnList2 = returnApplicationMapper.findAllByStatus(ConstVariables.DEPT_ADMIN_CHECK);
        List<ReturnApplicationVO> result = new ArrayList<>();
        for(ReturnApplication r:returnList1){
            if(r.getCreateTime().getTime()>=lasttime){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(r.getCreateTime());
                result.add(new ReturnApplicationVO(r.getReason(),r.getLocation(),r.getPredictReturnDate(),r.getStatus(),timeStr,r.getStudent().getId()));

            }
        }
        for(ReturnApplication r:returnList2){
            if(r.getCreateTime().getTime()>=lasttime){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(r.getCreateTime());
                result.add(new ReturnApplicationVO(r.getReason(),r.getLocation(),r.getPredictReturnDate(),r.getStatus(),timeStr,r.getStudent().getId()));
            }
        }
        return new Response<>(true,"查询成功",result);
    }

    @Override
    public Response<?> getMostReturnApplicationStudent(boolean all, String className, String deptName,int studentNum) {

        //查询
        List<StudentVO> result = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        if (all == true || (className.equals("")&&deptName.equals(""))) {
            studentList = studentMapper.findAll();
            result = new ArrayList<>();
        }
        else{
            if(className.equals("")){
                Department department = departmentMapper.findDepartmentByDeptName(deptName);
                for(Class c:department.getClassSet()){
                    studentList.addAll(c.getStudentSet());
                }
            }
            else {
                Class c = classMapper.findByClassName(className);
                studentList.addAll(c.getStudentSet());
            }
        }
        //排序申请次数
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < studentList.size() - i - 1; j++) {
                if (studentList.get(j).getReturnApplicationSet().size() < studentList.get(j).getReturnApplicationSet().size()) {
                    Student temp;
                    temp = studentList.get(j);
                    studentList.set(j, studentList.get(j + 1));
                    studentList.set(j + 1, temp);

                }
            }
        }
        int len=studentNum;
        if(studentList.size()<studentNum){
            len=studentList.size();
        }
        for (int i = 0; i < len; i++) {
            result.add(new StudentVO(studentList.get(i).getId(),studentList.get(i).getName()));
        }

        return new Response<>(true, "查询成功", result);
    }

    @Override
    public Response<?> getStudentOutSchool() {
        List<Student> studentList = studentMapper.findAllByStatus(ConstVariables.OUT_CAMPUS);
        List<StudentVO> result=new ArrayList<>();
        for (Student s:studentList){
            result.add(new StudentVO(s.getId(),s.getName()));
        }
        return new Response<>(true, "查询成功", result);
    }

    @Override
    public Response<?> getLongestTimeStudent(boolean all, String className, String deptName, int studentNum) {
        //查询
        List<Student> result = null;
        List<Student> studentList = new ArrayList<>();
        if (all == true || (className.equals("")&&deptName.equals(""))) {
            studentList = studentMapper.findAll();
            result = new ArrayList<>();
        }
        else{
            if(className.equals("")){
                Department department = departmentMapper.findDepartmentByDeptName(deptName);
                for(Class c:department.getClassSet()){
                    studentList.addAll(c.getStudentSet());
                }
            }
            else {
                Class c = classMapper.findByClassName(className);
                studentList.addAll(c.getStudentSet());
            }
        }
        //排序时长，未完成
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < studentList.size() - i - 1; j++) {
                if (studentList.get(j).getReturnApplicationSet().size() < studentList.get(j).getReturnApplicationSet().size()) {
                    Student temp;
                    temp = studentList.get(j);
                    studentList.set(j, studentList.get(j + 1));
                    studentList.set(j + 1, temp);

                }
            }
        }
        for (int i = 0; i < studentNum; i++) {
            result.add(studentList.get(i));
        }

        return new Response<>(true, "查询成功", result);
    }

}
