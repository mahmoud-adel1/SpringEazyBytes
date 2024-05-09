package com.eazybytes.main;

import com.eazybytes.beans.Person;
import com.eazybytes.config.ProjectConfig;
import com.eazybytes.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        System.out.println("Before retrieving the Person bean from spring context");
        Person person = context.getBean(Person.class);
        System.out.println("After retrieving the Person bean from spring context");

    }
}
