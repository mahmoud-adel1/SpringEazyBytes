package com.eazybytes.implementation;

import com.eazybytes.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {
    @Override
    public void rotate() {
        System.out.println("Michelin tyres rotate");
    }
}
