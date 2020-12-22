package com.example.demo.scheduler;

import com.example.demo.job.PrintJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: lingjian
 * @createDate: 2020/12/22 21:08
 */
public class MyScheduler {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        // 2、创建JobDetail实例，并与PrintJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintJob.class).withIdentity("job1", "group1").build();
        JobDataMap jobDataMap = new JobDataMap();

        JobDetail jobDetail1 =
                JobBuilder.newJob(PrintJob.class)
                        .usingJobData("msg", 123456789)
                        .usingJobData(jobDataMap)
                        .withIdentity("job2", "group2")
                        .build();

        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger =
                TriggerBuilder.newTrigger()
                        .withIdentity("trigger1", "triggerGroup1")
                        .startNow()
                        .withSchedule(
                                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                        .build();

        // 4、执行
        scheduler.scheduleJob(jobDetail1, trigger);
        scheduler.start();

        // 睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");
    }
}
