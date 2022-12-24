package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.DeptAdminService;
import com.example.demo.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/DeptAdmin")
public class DeptAdminController {

    private DeptAdminService deptAdminService;

    public DeptAdminController(DeptAdminService deptAdminService){
        this.deptAdminService = deptAdminService;
    }

    @GetMapping("/getleaveapplication")
    public Response<?> getLeaveApplication(@RequestBody JSONObject body){
        int dayNum = (Integer) body.get("dayNum");
        return deptAdminService.getNoApplyForLeave(dayNum);
    }
    @GetMapping("/getreturnapplication")
    public Response<?> getReturnApplication(@RequestBody JSONObject body){
        int dayNum = (Integer) body.get("dayNum");
        return deptAdminService.getNoApplyForReturn(dayNum);
    }

    @GetMapping("/getmostnumstudent")
    public  Response<?> getMostReturnApplicationStudent(@RequestBody JSONObject body){
        boolean all = (boolean) body.get("all");
        String className = (String) body.get("className");
        String  deptName = (String)  body.get("deptName");
        int studentNum = (int) body.get("studentNum");
        return deptAdminService.getMostReturnApplicationStudent(all,className,deptName,studentNum);
    }

    @GetMapping("/getLongtimestudent")//平均离校时间最长
    public  Response<?> getLongTimeLeaveStudent(@RequestBody JSONObject body){
        boolean all = (boolean) body.get("all");
        String className = (String) body.get("className");
        String  deptName = (String)  body.get("deptName");
        int studentNum = (int) body.get("studentNum");
        return deptAdminService.getLongestTimeStudent(all,className,deptName,studentNum);
    }

    @GetMapping("/getstudentoutschoolnotsubmitfor24")
    public Response<?> getstudentoutschoolnotsubmitfor24(){
        return  deptAdminService.getstudentoutschoolnotsubmitfor24();
    }

    @GetMapping("/getstudentoutschool")
    public Response<?> getStudentOutScholl(){
        return deptAdminService.getStudentOutSchool();
    }

    @GetMapping("/getnotleavestudent")
    public Response<?> getSubmitLeaveNotLeaveStudent(){
        return deptAdminService.getSubmitLeaveNotLeave();
    }

    @GetMapping("/getnoleavestudent")
    public Response<?> getNoLeaveStudent(@RequestBody JSONObject body){
        int dayNum = (Integer)body.get("day_num");
        String flag = (String) body.get("flag");
        String name = (String) body.get("name");
        if (flag.equals("全校")){
            return deptAdminService.getNotLeaveStudentInCampus(dayNum);
        }
        if (flag.equals("院系")){
            return deptAdminService.getNotLeaveStudentInDept(dayNum, name);
        }
        if (flag.equals("班级")){
            return deptAdminService.getNotLeaveStudentInClass(dayNum, name);
        }
        return null;
    }

    @GetMapping("/getsametimedailyreport")
    public Response<?> getSameTimeDailyReport(@RequestBody JSONObject body){
        int dayNum = (Integer) body.get("day_num");
        return deptAdminService.getSameTimeDailyReport(dayNum);
    }

    @GetMapping("/getmostlogcampus")
    public Response<?> getMostLogCampus(@RequestBody JSONObject body){
        int dayNum = (Integer) body.get("day_num");
        String deptName = (String) body.get("dept_name");
        return deptAdminService.getMostLogCampus(dayNum, deptName);
    }
}
