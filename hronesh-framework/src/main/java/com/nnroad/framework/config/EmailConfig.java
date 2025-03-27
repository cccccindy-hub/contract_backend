package com.nnroad.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "email")
@Component
@Data
public class EmailConfig {

    private List<String> its;

    private List<String> hrone;

    private String from;
}
