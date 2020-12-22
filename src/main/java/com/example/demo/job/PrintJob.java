package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.util.Random;

/**
 * @description: 打印输出job
 * @author: lingjian
 * @createDate: 2020/12/22 21:04
 */
public class PrintJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        long id = (long) jobDataMap.get("id");
        int day = (int) jobDataMap.get("day");

        LocalDate now = LocalDate.now();
        System.err.println("printJob start at:" + now + ", hello quartz-" + new Random().nextInt(100));
        LocalDate end = now.plusDays(day);
        System.err.println("printJob end at:" + end + ", id-" + id);
    }
}
