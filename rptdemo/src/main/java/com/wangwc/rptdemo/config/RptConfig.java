package com.wangwc.rptdemo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "report")
public class RptConfig {
    private List<String> selectList;
    private List<String> insertList;
    private List<String> cronList;
}
