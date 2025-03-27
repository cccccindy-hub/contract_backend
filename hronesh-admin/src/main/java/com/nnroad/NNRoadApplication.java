package com.nnroad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

/**
 * 启动程序
 *
 * @author nick
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NNRoadApplication {

    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        //设置时区
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        ConfigurableApplicationContext context = SpringApplication.run(NNRoadApplication.class, args);
        Environment env = context.getEnvironment();

        System.out.println("项目名称：" + env.getProperty("spring.application.name"));
        System.out.println("项目端口号：" + env.getProperty("server.port"));
    }
}
