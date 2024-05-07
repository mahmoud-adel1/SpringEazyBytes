package com.example.main;

import com.example.beans.Vehicle;
import com.example.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class Example7 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle volkswagen = new Vehicle();
        volkswagen.setName("Volkswagen");

        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;

        Supplier<Vehicle> audoSupplier = () -> {
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        };

        Random random = new Random();
        int number = random.nextInt(10);
        System.out.println("Random Number = " + number);
        if(number % 2 == 0) {
            context.registerBean("volkswagen", Vehicle.class,volkswagenSupplier);
        }else {
            context.registerBean("audi", Vehicle.class,audoSupplier);
        }

        Vehicle volksVehicle = null;
        Vehicle audiVehicle = null;
        try {
            volksVehicle = context.getBean("volkswagen", Vehicle.class);
        }catch (NoSuchBeanDefinitionException exception) {
            System.out.println("Error while creating Volkswagen vehicle");
        }

        try{
            audiVehicle = context.getBean("audi", Vehicle.class);
        }catch (NoSuchBeanDefinitionException exception) {
            System.out.println("Error while creating Audi vehicle");

        }

        if(null != volksVehicle) {
            System.out.println("Programming vehicle name from spring context is: " + volksVehicle.getName());
        }else {
            System.out.println("Programming vehicle name from spring context is: " + audiVehicle.getName());
        }


    }
}
