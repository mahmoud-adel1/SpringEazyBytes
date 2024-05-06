package com.example.config;

import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {

    @Bean(name = "audiVehicle")
    Vehicle vehicle1() {
        var vehicle = new Vehicle();
        vehicle.setName("Audi");
        return vehicle;
    }

    @Bean(value = "hondaVehicle")
    Vehicle vehicle2() {
        var vehicle = new Vehicle();
        vehicle.setName("Honda");
        return vehicle;
    }

    @Primary
    @Bean("ferrariVehicle")
    Vehicle vehicle3() {
        var vehicle = new Vehicle();
        vehicle.setName("Ferrari");
        return vehicle;
    }


}
