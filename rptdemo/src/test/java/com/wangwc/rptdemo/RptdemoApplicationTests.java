package com.wangwc.rptdemo;

import com.wangwc.rptdemo.config.RptConfig;
import com.wangwc.rptdemo.service.impl.RptServiceImpl;
import com.wangwc.rptdemo.util.EncryptUtil;
import com.wangwc.rptdemo.scheduler.RptScheduler;
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
    RptScheduler rptScheduler;

    @Test
    void contextLoads() {
    }

    /**
     * 在此处生成加密后的数据库密码
     */
    @Test
    void testEncrypt(){
        encryptUtil.encrypt("123456");
    }

    @Test
    void testService(){
//        testService.getCfg();
//        System.out.println(testService.getAll().toString());
    }

    @Test
    void testConfig(){
        System.out.println(rptConfig.getSelectStmts().size());
        System.out.println(rptConfig.getCronList().get(0));
    }

    @Test
    void testJob() throws SchedulerException {
        rptScheduler.schedule();
    }

}
