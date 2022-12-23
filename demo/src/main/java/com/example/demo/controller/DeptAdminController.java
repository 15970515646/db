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
        int studentNum = (int) body.get("syudentNum");
        return deptAdminService.getLongestTimeStudent(all,className,deptName,studentNum);
    }

    @GetMapping("/getstudentoutschool")
    public Response<?> getStudentOutScholl(){
        return deptAdminService.getStudentOutSchool();
    }

    //问题1：众多的进出校园log中，怎么去辨别最新的，第五点有要求离校超过24小时，肯定要知道最后那个离校日志才能确定
    //问题2：第三点中，平均离校时间，应该就是比较相同的一段时间内比如一年离校的总时长，所以每次的时长怎么获得，要找邻近的一次出校一次入校log时间之差，感觉好复杂
    //其他几点测试已经简略完成，就是3，5两个，3代码架子搭起来了，就差上面说的如何确定时间，5可在4的基础上变化一下就行，也是确定时间问题
}
