package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle vehicle = context.getBean("vehicle1",Vehicle.class);

        System.out.println("Vehicle value from spring context: " + vehicle.getName());


    }
}
