package com.eazybytes.implementation;

import com.eazybytes.interfaces.Speakers;
import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers {
    @Override
    public void makeSound() {
        System.out.println("Bose speakers make sound");
    }
}
