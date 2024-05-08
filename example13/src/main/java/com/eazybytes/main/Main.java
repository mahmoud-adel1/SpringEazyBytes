package com.eazybytes.main;

import com.eazybytes.beans.Person;
import com.eazybytes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean("person",Person.class);
        person.getVehicle().getVehicleServices().getSpeakers().makeSound();
        person.getVehicle().getVehicleServices().getTyres().rotate();

    }
}
