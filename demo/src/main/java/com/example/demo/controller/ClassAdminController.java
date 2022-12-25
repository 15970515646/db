package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ClassAdminService;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classadmin")
public class ClassAdminController {
    private ClassAdminService classAdminService;

    @Autowired
    public ClassAdminController(ClassAdminService classAdminService) {
        this.classAdminService = classAdminService;
    }

    @GetMapping("/getstudentapplication")
    public Response<?> getStudentApplication(@RequestBody JSONObject body){
        String teacherId = (String) body.get("teacher_id");
        return classAdminService.getStudentApplication(teacherId);
    }
    @PostMapping("/examineleaveapplication")
    public Response<?> classAdminExamineLeave(@RequestBody JSONObject body){
        String applicationId = (String) body.get("application_id");
        String operation = (String) body.get("operation");
        return classAdminService.examineLeave(applicationId,operation);
    }
    @PostMapping("/examinereturnapplication")
    public Response<?> classAdminExamineReturn(@RequestBody JSONObject body){
        String applicationId = (String) body.get("application_id");
        String operation = (String) body.get("operation");
        return classAdminService.examineReturn(applicationId, operation);
    }
}
