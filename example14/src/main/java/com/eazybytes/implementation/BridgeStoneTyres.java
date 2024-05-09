package com.eazybytes.implementation;

import com.eazybytes.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BridgeStoneTyres implements Tyres {

    @Override
    public void rotate() {
        System.out.println("Bridgestone tyres rotate");
    }
}
