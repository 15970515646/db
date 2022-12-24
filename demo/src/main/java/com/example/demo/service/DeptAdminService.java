package com.example.demo.service;

import com.example.demo.utils.Response;

public interface DeptAdminService {

    public Response<?> getNoApplyForLeave(int day_num);

    public Response<?> getNoApplyForReturn(int day_num);

    public Response<?> getMostReturnApplicationStudent(boolean all, String className, String deptName,int studentNum);

    public Response<?> getStudentOutSchool();

    public Response<?> getLongestTimeStudent(boolean all, String className, String deptName, int studentNum);

    public Response<?> getSubmitLeaveNotLeave();
}
