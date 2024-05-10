package com.eazybytes.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("@annotation(com.eazybytes.interfaces.LogAspect)")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Instant start = Instant.now();
        logger.info(proceedingJoinPoint.getSignature().toString() + " method execution start");
        proceedingJoinPoint.proceed();
        Instant finish= Instant.now();
        long timeElapsed = Duration.between(start,finish).toMillis();
        logger.info("Time took to execute the method : " + timeElapsed);
        logger.info(proceedingJoinPoint.getSignature().getName() + " method execution end");
    }

    @AfterThrowing(value = "execution(* com.eazybytes.services.*.*(..))",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception) {
        logger.log(Level.SEVERE,joinPoint.getSignature()+" An exception thrown with the help of " +
                " @AfterThrowing which happened due to " + exception.getMessage());
    }

    @AfterReturning(value = "execution(* com.eazybytes.services.*.*(..))", returning = "object")
    public void logStatus(JoinPoint joinPoint, Object object) {
        logger.log(Level.SEVERE,joinPoint.getSignature() + " Method successfully processed with the status " +
                object.toString());
    }
}
