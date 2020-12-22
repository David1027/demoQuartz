package com.example.demo.service.impl;

import com.example.demo.job.PrintJob;
import com.example.demo.service.HelloService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lingjian
 * @createDate: 2020/12/22 21:49
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void test(Long id) {
        // 1、创建调度器Scheduler
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = stdSchedulerFactory.getScheduler();

            // 2、创建JobDetail实例，并与PrintJob类绑定(Job执行内容)
            JobDataMap jobDataMap = new JobDataMap();

            JobDetail jobDetail1 =
                    JobBuilder.newJob(PrintJob.class)
                            .usingJobData("msg", id)
                            .usingJobData(jobDataMap)
                            .withIdentity("job" + id, "group1")
                            .build();

            // 3、构建Trigger实例,每隔1s执行一次
            Trigger trigger =
                    TriggerBuilder.newTrigger()
                            .withIdentity("trigger" + id, "triggerGroup1")
                            .startNow()
                            .withSchedule(
                                    SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                            .build();

            // 4、执行
            scheduler.scheduleJob(jobDetail1, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
