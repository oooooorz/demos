package com.wangwc.rptdemo;

import com.wangwc.rptdemo.config.RptConfig;
import com.wangwc.rptdemo.service.impl.RptServiceImpl;
import com.wangwc.rptdemo.util.EncryptUtil;
import com.wangwc.rptdemo.util.JobUtil;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RptdemoApplicationTests {

    @Autowired
    EncryptUtil encryptUtil;

    @Autowired
    RptServiceImpl testService;

    @Autowired
    RptConfig rptConfig;

    @Autowired
    JobUtil jobUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void testUtil(){
        encryptUtil.encrypt("wang");
        encryptUtil.encrypt("123456");
    }

    @Test
    void testService(){
//        testService.getCfg();
//        System.out.println(testService.getAll().toString());
    }

    @Test
    void testConfig(){
        System.out.println(rptConfig.getSelectList().size());
        System.out.println(rptConfig.getCronList().get(0));
    }

    @Test
    void testJob() throws SchedulerException {
        jobUtil.schedule();
    }

}
