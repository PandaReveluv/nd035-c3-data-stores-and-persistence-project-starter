package com.udacity.jdnd.course3.critter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    PetProperties petProperties;

    @Bean
    @Primary
    public DataSource getPetDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(petProperties.getUrl());
        dataSourceBuilder.username(petProperties.getUsername());
        dataSourceBuilder.password(petProperties.getPassword());
        return dataSourceBuilder.build();
    }
}
