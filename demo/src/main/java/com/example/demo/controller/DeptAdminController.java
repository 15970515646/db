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
        int studentNum = (int) body.get("syudentNum");
        return deptAdminService.getMostReturnApplicationStudent(all,className,deptName,studentNum);
    }

    @GetMapping("/getLongtimestudent")//平均离校时间最长
    public  Response<?> getLongTimeLeaveStudent(@RequestBody JSONObject body){
        boolean all = (boolean) body.get("all");
        String className = (String) body.get("className");
        String  deptName = (String)  body.get("deptName");
        int studentNum = (int) body.get("syudentNum");
        return deptAdminService.getLongestTimeStudent(all,className,deptName,studentNum);
    }

    @GetMapping("/getstudentoutschool")
    public Response<?> getStudentOutScholl(){
        return deptAdminService.getStudentOutSchool();
    }
}
