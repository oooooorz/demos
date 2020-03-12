package com.wangwc.rptdemo.service.impl;

import com.wangwc.rptdemo.service.RptService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RptServiceImpl implements RptService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRpt(String selectStmt, String insertStmt){
        List<Map> rptList = sqlSessionTemplate.selectList(selectStmt);
        for(Map res : rptList){
            System.out.println(res);
        }
        System.out.println("查询完成: "+selectStmt);
        int res = sqlSessionTemplate.insert(insertStmt, rptList);
        if (res==-1){
            throw new RuntimeException("插入失败: "+insertStmt);
        }
        System.out.println("插入成功: "+insertStmt);
    }
}
