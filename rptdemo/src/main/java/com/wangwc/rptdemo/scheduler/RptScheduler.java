package com.wangwc.rptdemo.scheduler;

import com.wangwc.rptdemo.config.RptConfig;
import com.wangwc.rptdemo.scheduler.jobs.RptJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 任务调度工具类
 */
@Component
public class RptScheduler {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private RptConfig rptConfig;

    private List<String> selectStmts;

    private List<String> insertStmts;

    private List<String> cronList;

    public void scheduleJob(Scheduler scheduler) throws SchedulerException {
        for(int i=0; i<cronList.size(); i++){
            System.out.println(selectStmts.get(i));
            System.out.println(insertStmts.get(i));
            System.out.println(cronList.get(i));
            JobDetail jobDetail = JobBuilder.newJob(RptJob.class).
                    withIdentity("job"+i, "group"+i).
                    usingJobData("select", selectStmts.get(i)).
                    usingJobData("insert", insertStmts.get(i)).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronList.get(i));
            CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().
                    withIdentity("trigger"+i,"group"+i).
                    withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    public void schedule() throws SchedulerException {
        selectStmts = rptConfig.getSelectStmts();
        insertStmts = rptConfig.getInsertStmts();
        cronList = rptConfig.getCronList();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.clear();
        scheduleJob(scheduler);
    }
}
