package com.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
@Component(value = "vehicle0")
public class Vehicle {

    private String name;

    public Vehicle() {
        System.out.println("Vehicle bean created by spring");
        this.name = "Toyota";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle name is - " + name;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Vehicle Bean");
    }

    public void printHello() {
        System.out.println("Printing Hello from Component Vehicle Bean");
    }
}
