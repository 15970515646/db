package com.example.demo.service;

import com.example.demo.utils.Response;

public interface ClassAdminService {
    public Response<?> getStudentApplication(String teacherId);
    public Response<?> examineLeave(String applicationId, String operation);
    public Response<?> examineReturn(String applicationId, String operation);
}
