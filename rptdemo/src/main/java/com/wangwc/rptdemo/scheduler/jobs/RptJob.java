package com.wangwc.rptdemo.scheduler.jobs;

import com.wangwc.rptdemo.service.impl.RptServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RptJob implements Job {

    @Autowired
    private RptServiceImpl rptService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String selectStmt = jobExecutionContext.getJobDetail().getJobDataMap().getString("select");
        String insertStmt = jobExecutionContext.getJobDetail().getJobDataMap().getString("insert");
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobGroup = jobExecutionContext.getJobDetail().getKey().getGroup();
        System.out.println("jobname: "+jobName);
        System.out.println("jobgroup: "+jobGroup);
        rptService.createRpt(selectStmt, insertStmt);
    }
}
