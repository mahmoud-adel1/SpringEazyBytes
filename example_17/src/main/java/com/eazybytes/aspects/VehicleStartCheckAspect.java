package com.eazybytes.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class VehicleStartCheckAspect {
    private Logger logger = Logger.getLogger(VehicleStartCheckAspect.class.getName());

    @Before("execution(* com.eazybytes.services.*.*(..)) && args(vehicleStarted2,..)")
    public void check(JoinPoint joinPoint, boolean vehicleStarted1) throws Throwable{
        if(!vehicleStarted1) {
            throw new RuntimeException("Vehicle not start");
        }
    }

}
