package com.wangwc.rptdemo.util;

import com.wangwc.rptdemo.config.RptConfig;
import com.wangwc.rptdemo.job.RptJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务调度工具类
 */
@Component
public class JobUtil {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private RptConfig rptConfig;

    List<String> selectList;

    List<String> insertList;

    List<String> cronList;

    public void scheduleJob(Scheduler scheduler) throws SchedulerException {
        for(int i=0; i<cronList.size(); i++){
            System.out.println(selectList.get(i));
            System.out.println(insertList.get(i));
            System.out.println(cronList.get(i));
            JobDetail jobDetail = JobBuilder.newJob(RptJob.class).
                    withIdentity("job"+i, "group"+i).
                    usingJobData("select", selectList.get(i)).
                    usingJobData("insert",insertList.get(i)).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronList.get(i));
            CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().
                    withIdentity("trigger"+i,"group"+i).
                    withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    public void schedule() throws SchedulerException {
        selectList = rptConfig.getSelectStmts();
        insertList = rptConfig.getInsertStmts();
        cronList = rptConfig.getCronList();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.clear();
        scheduleJob(scheduler);
    }
}
