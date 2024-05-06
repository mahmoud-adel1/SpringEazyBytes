package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle vehicle = context.getBean(Vehicle.class);
        String hello = context.getBean(String.class);
        Integer num = context.getBean(Integer.class);
        System.out.println("Vehicle value from spring context: " + vehicle.getName());
        System.out.println("String value from spring context: " + hello);
        System.out.println("integer value from spring context: " + num);

    }
}
