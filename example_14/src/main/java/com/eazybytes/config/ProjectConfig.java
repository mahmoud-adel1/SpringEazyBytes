package com.eazybytes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.eazybytes.implementation","com.eazybytes.services"})
@ComponentScan(basePackageClasses = {com.eazybytes.beans.Person.class,com.eazybytes.beans.Vehicle.class})
public class ProjectConfig {

}
