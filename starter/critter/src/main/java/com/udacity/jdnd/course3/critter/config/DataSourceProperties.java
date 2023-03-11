package com.udacity.jdnd.course3.critter.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
}
