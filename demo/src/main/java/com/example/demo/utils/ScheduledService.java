package com.example.demo.utils;

import com.example.demo.entity.DailyReport;
import com.example.demo.entity.Student;
import com.example.demo.mapper.DailyReportMapper;
import com.example.demo.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Component
public class ScheduledService {
    StudentMapper studentMapper;
    DailyReportMapper dailyReportMapper;

    public ScheduledService(StudentMapper studentMapper, DailyReportMapper dailyReportMapper) {
        this.studentMapper = studentMapper;
        this.dailyReportMapper = dailyReportMapper;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void checkDailyReport(){
        log.info("每天凌晨一点查看每日填报情况");
        List<Student> students = studentMapper.findAll();
        for (Student student : students){
            List<DailyReport> dailyReports = dailyReportMapper.findByStudentOrderByCreateTime(student);
            DailyReport dailyReport = dailyReports.get(dailyReports.size()-1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Long today = timestamp.getTime()/ConstVariables.MILLI_TO_DAY;
            Long lastDay = dailyReport.getCreateTime().getTime()/ConstVariables.MILLI_TO_DAY;
            if (today - lastDay>1.1 || !dailyReport.getLocation().equals("上海") || dailyReport.getHealthy_status().equals("异常")){
                System.out.println(student.getName());
                System.out.println(today);
                System.out.println(lastDay);
                student.setStatus(ConstVariables.NO_PERMISSION);
                studentMapper.save(student);
            }
        }
    }
}
