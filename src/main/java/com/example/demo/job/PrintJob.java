package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @description: 打印输出job
 * @author: lingjian
 * @createDate: 2020/12/22 21:04
 */
public class PrintJob implements Job {
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    LocalDateTime now = LocalDateTime.now();
    System.err.println("printJob start at:" + now + ", hello quartz-" + new Random().nextInt(100));
      JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
      long msg = (long) jobDataMap.get("msg");
      System.err.println(msg);
  }
}
