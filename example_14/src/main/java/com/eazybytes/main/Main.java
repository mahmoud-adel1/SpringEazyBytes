package com.eazybytes.main;

import com.eazybytes.beans.Person;
import com.eazybytes.config.ProjectConfig;
import com.eazybytes.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        VehicleServices vehicleServices1 = context.getBean(VehicleServices.class);
        VehicleServices vehicleServices2 = context.getBean(VehicleServices.class);
        System.out.println("Hashcode of the object vehicleServices1: " + vehicleServices1.hashCode());
        System.out.println("Hashcode of the object vehicleServices2: " + vehicleServices2.hashCode());
        if(vehicleServices1.hashCode() == vehicleServices2.hashCode()) {
            System.out.println("VehicleServices bean is a singleton scoped bean");
        }
    }
}
