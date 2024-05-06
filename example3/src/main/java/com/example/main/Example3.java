package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle audi = context.getBean("audiVehicle",Vehicle.class);
        Vehicle honda = context.getBean("hondaVehicle",Vehicle.class);
        Vehicle ferrari = context.getBean("ferrariVehicle",Vehicle.class);

        System.out.println("Vehicle value from spring context: " + audi.getName());
        System.out.println("Vehicle value from spring context: " + honda.getName());
        System.out.println("Vehicle value from spring context: " + ferrari.getName());


    }
}
