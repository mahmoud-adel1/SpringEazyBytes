package com.eazybytes.implementation;

import com.eazybytes.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BridgeStoneTyres implements Tyres {

    @Override
    public String rotate() {
        return "Vehicle moving with the help of BridgeStone tyres";
    }

    @Override
    public String stop() {
        return "Vehicle stop with the help of BridgeStone tyres";
    }
}
