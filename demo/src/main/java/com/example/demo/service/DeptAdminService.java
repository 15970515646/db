package com.example.demo.service;

import com.example.demo.utils.Response;

public interface DeptAdminService {

    public Response<?> getNoApplyForLeave(int day_num);

    public Response<?> getNoApplyForReturn(int day_num);

    public Response<?> getMostReturnApplicationStudent(boolean all, String className, String deptName,int studentNum);

    public Response<?> getStudentOutSchool();

    public Response<?> getLongestTimeStudent(boolean all, String className, String deptName, int studentNum);

    public Response<?> getSubmitLeaveNotLeave();

    public Response<?> getNotLeaveStudentInCampus(int dayNum);

    public Response<?> getNotLeaveStudentInDept(int dayNum, String deptName);

    public Response<?> getNotLeaveStudentInClass(int dayNum, String className);

    public Response<?> getSameTimeDailyReport(int dayNum);

    public Response<?> getMostLogCampus(int dayNum, String deptName);

    public Response<?> getApplications(String teacherId);

    public Response<?> examineLeave(String applicationId, String operation);
    public Response<?> examineReturn(String applicationId, String operation);

}
