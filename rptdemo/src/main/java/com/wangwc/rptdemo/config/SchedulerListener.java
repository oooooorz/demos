package com.wangwc.rptdemo.config;

import com.wangwc.rptdemo.util.JobUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 启动quartz定时任务
 */
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    JobUtil jobUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try{
            jobUtil.schedule();
        }catch(SchedulerException e){
            e.printStackTrace();
        }
    }
}
