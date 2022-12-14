package com.example.demo.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.VOclass.LeaveApplicationVO;
import com.example.demo.VOclass.LeaveStudentVO;
import com.example.demo.VOclass.ReturnApplicationVO;
import com.example.demo.VOclass.StudentVO;
import com.example.demo.entity.*;
import com.example.demo.entity.Class;
import com.example.demo.mapper.*;
import com.example.demo.service.DeptAdminService;
import com.example.demo.utils.ConstVariables;
import com.example.demo.utils.Response;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.utils.ConstVariables.MILLI_TO_DAY;

@Service
public class DeptAdminServiceimpl implements DeptAdminService {

    LeaveApplicationMapper leaveApplicationMapper;

    ClassMapper classMapper;
    DepartmentMapper departmentMapper;
    StudentMapper studentMapper;
    ReturnApplicationMapper returnApplicationMapper;
    StudentLogMapper studentLogMapper;
    DailyReportMapper dailyReportMapper;
    DeptAdminMapper deptAdminMapper;

    public DeptAdminServiceimpl(LeaveApplicationMapper leaveApplicationMapper, ClassMapper classMapper, DepartmentMapper departmentMapper, StudentMapper studentMapper, ReturnApplicationMapper returnApplicationMapper, StudentLogMapper studentLogMapper, DailyReportMapper dailyReportMapper,DeptAdminMapper deptAdminMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.classMapper = classMapper;
        this.departmentMapper = departmentMapper;
        this.studentMapper = studentMapper;
        this.returnApplicationMapper = returnApplicationMapper;
        this.studentLogMapper = studentLogMapper;
        this.dailyReportMapper = dailyReportMapper;
        this.deptAdminMapper = deptAdminMapper;
    }

    long    computerAllTime(Student s){
        List<StudentLog> list = studentLogMapper.findByStudent(s);
        int length = list.size();
        long totalTime = 0;
        int count = 0;
        for (int i=0;i<length-1;i++) {
            if (list.get(i).getAction().equals(ConstVariables.OUT_CAMPUS)) {
                totalTime += list.get(i + 1).getCreateTime().getTime() -
                        list.get(i).getCreateTime().getTime() ;
                count++;
            }

        }
        if(count==0){
            return 0;
        }
        return totalTime/count;
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
        return new Response<>(true,"????????????",result);
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
        return new Response<>(true,"????????????",result);
    }

    @Override
    public Response<?> getMostReturnApplicationStudent(boolean all, String className, String deptName,int studentNum) {

        //??????
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
        //??????????????????
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < studentList.size() - i - 1; j++) {
                if (studentList.get(j).getReturnApplicationSet().size() < studentList.get(j+1).getReturnApplicationSet().size()) {
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

        return new Response<>(true, "????????????", result);
    }

    @Override
    public Response<?> getStudentOutSchool() {
        List<Student> studentList = studentMapper.findAll();
        List<LeaveStudentVO> result=new ArrayList<>();
        for (Student s:studentList){
            List<StudentLog> studentLogList = studentLogMapper.findByStudent(s);
            if(studentLogList.size()==0) {
                continue;
            }
            if(studentLogList.get(studentLogList.size()-1).getAction().equals(ConstVariables.OUT_CAMPUS)){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(studentLogList.get(studentLogList.size()-1).getCreateTime());
                result.add(new LeaveStudentVO(s.getId(),s.getName(),timeStr));
            }

        }
        return new Response<>(true, "????????????", result);
    }


    @Override
    public Response<?> getLongestTimeStudent(boolean all, String className, String deptName, int studentNum) {
        //??????
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
        //????????????
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < studentList.size() - i - 1; j++) {
                if (computerAllTime(studentList.get(j))<computerAllTime(studentList.get(j+1))) {
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

        return new Response<>(true, "????????????", result);
    }
    @Override
    public Response<?> getstudentoutschoolnotsubmitfor24() {
        List<StudentVO> result = new ArrayList<>();
        List<Student> studentList = studentMapper.findAll();
        for(Student s:studentList){
            List<StudentLog> logList = studentLogMapper.findByStudent(s);
            List<LeaveApplication> leaveApplicationList = leaveApplicationMapper.findByStudent(s);
            if(logList.size()==0||leaveApplicationList.size()==0){
                return new Response<>(true, "????????????", result);
            }
            if(logList.get(logList.size()-1).getAction().equals(ConstVariables.OUT_CAMPUS)){
                if(leaveApplicationList.get(leaveApplicationList.size()-1).getStatus().equals(ConstVariables.APPROVE_APPLY)&&leaveApplicationList.get(leaveApplicationList.size()-1).getStatus().equals(ConstVariables.REFUSE_APPLY)){
                    Timestamp time=new Timestamp(System.currentTimeMillis());
                    if(time.getTime()-logList.get(logList.size()-1).getCreateTime().getTime()>24*ConstVariables.MILLI_TO_HOUR){
                        result.add(new StudentVO(s.getId(),s.getName()));
                    }
                }
            }
        }
        return new Response<>(true, "????????????", result);
    }

    @Override
    public Response<?> getSubmitLeaveNotLeave() {
        List<LeaveApplication> allLeaveApplications = leaveApplicationMapper.findAll();
        List<Student> studentList = new ArrayList<>();
//        Student student = studentMapper.findById("19307110197");
//        List<StudentLog> studentLogs = studentLogMapper.findByStudent(student);
//        if (studentLogs.size()==0){
//            return new Response<>(false, "test");
//        }
        for(LeaveApplication leaveApplication : allLeaveApplications){
            Student student = leaveApplication.getStudent();
            List<StudentLog> studentLogs = studentLogMapper.findByStudent(student);
            if(studentLogs.size()!=0){
                if (studentLogs.get(studentLogs.size()-1).getAction().equals(ConstVariables.IN_CAMPUS)){
                    studentList.add(student);
                }
            }

        }
        JSONObject result = new JSONObject();
        result.put("????????????",studentList);
        result.put("????????????", studentList.size());
        return new Response<>(true, "??????????????????",result);
    }

    @Override
    public Response<?> getNotLeaveStudentInCampus(int dayNum) {
        List<Student> studentList = studentMapper.findAll();
        List<StudentVO> result = new ArrayList<>();

        if (studentList.size()==0){
            return new Response<>(false, "no student");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;
        for (Student student : studentList){
            int flag =0;
            List<StudentLog>studentLogs = studentLogMapper.findByStudent(student);
            if(studentLogs.size()>0 && studentLogs.get(studentLogs.size()-1).getAction().equals(ConstVariables.IN_CAMPUS)){
                for(StudentLog studentLog : studentLogs){
                    if ((today-studentLog.getCreateTime().getTime())/ MILLI_TO_DAY<dayNum+1)
                        flag=1;
                }
                if (flag==0)
                    result.add(new StudentVO(student.getId(),student.getName()));
            }
        }
        return new Response<>(true,"????????????",result);
    }

    @Override
    public Response<?> getNotLeaveStudentInDept(int dayNum, String deptName) {
        Department department = departmentMapper.findDepartmentByDeptName(deptName);
        List<Class> classList = classMapper.findByDepartment(department);
        List<Student> studentList = new ArrayList<>();
        for (Class aClass : classList){
            List<Student> students = studentMapper.findByAclass(aClass);
            studentList.addAll(students);
        }
        if (studentList.size()==0)
            return new Response<>(false,"????????????????????????");
        List<StudentVO> result = new ArrayList<>();

        if (studentList.size()==0){
            return new Response<>(false, "no student");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;
        for (Student student : studentList){
            int flag =0;
            List<StudentLog>studentLogs = studentLogMapper.findByStudent(student);
            if(studentLogs.size()>0 && studentLogs.get(studentLogs.size()-1).getAction().equals(ConstVariables.IN_CAMPUS)){
                for(StudentLog studentLog : studentLogs){
                    if (today-studentLog.getCreateTime().getTime()/ MILLI_TO_DAY<dayNum+1)
                        flag=1;
                }
                if (flag==0)
                    result.add(new StudentVO(student.getId(),student.getName()));
            }
        }
        return new Response<>(true,"????????????",result);
    }

    @Override
    public Response<?> getNotLeaveStudentInClass(int dayNum, String className) {
        Class newClass = classMapper.findByClassName(className);
        List<Student> studentList = studentMapper.findByAclass(newClass);
        if (studentList.size()==0){
            return new Response<>(false,"????????????????????????");
        }
        List<StudentVO> result = new ArrayList<>();

        if (studentList.size()==0){
            return new Response<>(false, "no student");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;
        for (Student student : studentList){
            int flag =0;
            List<StudentLog>studentLogs = studentLogMapper.findByStudent(student);
            if(studentLogs.size()>0 && studentLogs.get(studentLogs.size()-1).getAction().equals(ConstVariables.IN_CAMPUS)){
                for(StudentLog studentLog : studentLogs){
                    if (today-studentLog.getCreateTime().getTime()/ MILLI_TO_DAY<dayNum+1)
                        flag=1;
                }
                if (flag==0)
                    result.add(new StudentVO(student.getId(),student.getName()));
            }
        }
        return new Response<>(true,"????????????",result);
    }

    @Override
    public Response<?> getSameTimeDailyReport(int dayNum) {
        List<Student> allStudent = studentMapper.findAll();
        List<Student> result = new ArrayList<>();
        for(int i=0;i<allStudent.size();i++){
            List<Student> temp = new ArrayList<>();
            for(int j=i+1;j<allStudent.size();j++){
                temp.add(allStudent.get(j));
            }
            result.addAll(compareDailyReport(allStudent.get(i),temp,dayNum));
        }
        JSONObject res = new JSONObject();
        res.put("????????????",result.size());
        res.put("????????????",result);
        return new Response<>(true, "????????????", res);
    }

    @Override
    public Response<?> getMostLogCampus(int dayNum, String deptName) {
        Map<String, Integer> map = new HashMap<>();
        map.put(ConstVariables.HANDAN_CAMPUS,0);
        map.put(ConstVariables.JIANGWAN_CAMPUS,0);
        map.put(ConstVariables.ZHANGJIANG_CAMPUS,0);
        map.put(ConstVariables.FENGLIN_CAMPUS,0);

        Department department = departmentMapper.findDepartmentByDeptName(deptName);
        List<Class> classList = classMapper.findByDepartment(department);
        List<Student> studentList = new ArrayList<>();
        for (Class aClass : classList){
            List<Student> students = studentMapper.findByAclass(aClass);
            studentList.addAll(students);
        }
        for (Student student : studentList){
            List<StudentLog> studentLogs = studentLogMapper.findByStudent(student);
            for(StudentLog studentLog:studentLogs){
                map.put(studentLog.getCampusName(),map.get(studentLog.getCampusName())+1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        return new Response<>(true, "????????????", list.get(3).getKey());

    }


    @Override
    public Response<?> getApplications(String teacherId) {
        DeptAdmin deptAdmin = deptAdminMapper.findByTeacherId(teacherId);
        if (deptAdmin==null)
            return new Response<>(false,"???????????????????????????");
        Department department = deptAdmin.getDepartment();

        List<Class> classList = classMapper.findByDepartment(department);
        List<Student> studentList = new ArrayList<>();
        for (Class aClass : classList){
            List<Student> students = studentMapper.findByAclass(aClass);
            studentList.addAll(students);
        }
        if (studentList.size()==0)
            return new Response<>(false,"????????????????????????");
        List<ReturnApplicationVO> returnApplications = new ArrayList<>();
        List<LeaveApplicationVO> leaveApplications = new ArrayList<>();
        for(Student student : studentList){
            for(LeaveApplication leaveApplication : leaveApplicationMapper.findByStudentAndStatus(student, ConstVariables.DEPT_ADMIN_CHECK)){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = df.format(leaveApplication.getCreateTime());
                leaveApplications.add(new LeaveApplicationVO(leaveApplication.getId(),leaveApplication.getReason(), leaveApplication.getDestination(), leaveApplication.getPredictReturnDate(),leaveApplication.getPredictLeaveDate(),leaveApplication.getStatus(),timeStr,leaveApplication.getStudent().getId()));
            }
            for(ReturnApplication returnApplication : returnApplicationMapper.findByStudentAndStatus(student, ConstVariables.DEPT_ADMIN_CHECK)){
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
        if (!leaveApplication.getStatus().equals(ConstVariables.DEPT_ADMIN_CHECK))
            return new Response<>(false,"?????????????????????????????????????????????");
        if (operation.equals(ConstVariables.APPROVE_APPLY)){
            leaveApplication.setStatus(ConstVariables.APPLICATION_PASS);
            leaveApplicationMapper.save(leaveApplication);
        }
        else {
            leaveApplication.setStatus(ConstVariables.APPLICATION_REFUSED);
            leaveApplicationMapper.save(leaveApplication);
        }
        return new Response<>(true,"???????????????????????????????????????");
    }



    @Override
    public Response<?> examineReturn(String applicationId, String operation) {
        ReturnApplication returnApplication = returnApplicationMapper.findById(applicationId);
        if(returnApplication==null)
            return new Response<>(false,"??????ID??????");
        if (!returnApplication.getStatus().equals(ConstVariables.DEPT_ADMIN_CHECK))
            return new Response<>(false,"?????????????????????????????????????????????");
        if (operation.equals(ConstVariables.APPROVE_APPLY)){
            returnApplication.setStatus(ConstVariables.APPLICATION_PASS);
            returnApplicationMapper.save(returnApplication);
        }
        else {
            returnApplication.setStatus(ConstVariables.APPLICATION_REFUSED);
            returnApplicationMapper.save(returnApplication);
        }
        return new Response<>(true,"???????????????????????????????????????");

    }


    private List<Student> compareDailyReport(Student student, List<Student> studentList, int dayNum){
        List<Student> students = new ArrayList<>();

        for (Student student1 : studentList){
            if(compareStudent(student,student1,dayNum)){
                students.add(student1);
            }
        }
        if (students.size()!=0)
            students.add(student);
        return students;
    }
    private boolean compareStudent(Student student1, Student student2, int dayNum){
        List<DailyReport> student1s = dailyReportMapper.findByStudent(student1);
        List<DailyReport> student2s = dailyReportMapper.findByStudent(student2);
        List<DailyReport> result1 = new ArrayList<>();
        List<DailyReport> result2 = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;

        for (DailyReport dailyReport : student1s){
            if (today - dailyReport.getCreateTime().getTime()/ MILLI_TO_DAY<dayNum+1){
                result1.add(dailyReport);
            }
        }
        for (DailyReport dailyReport : student2s){
            if (today - dailyReport.getCreateTime().getTime()/ MILLI_TO_DAY<dayNum+1){
                result2.add(dailyReport);
            }
        }
        if(result1.size()==0 || result2.size()==0)
            return false;
        if(result1.size()!=result2.size())
            return false;
        for(int i=0;i<result1.size();i++){
            if (result1.get(i).getCreateTime().getTime()/ConstVariables.MILLI_TO_MINUTE!=result2.get(i).getCreateTime().getTime()/ConstVariables.MILLI_TO_MINUTE)
                return false;
        }
        return true;
    }
}
