package com.eazybytes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.eazybytes.implementation","com.eazybytes.services","com.eazybytes.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
