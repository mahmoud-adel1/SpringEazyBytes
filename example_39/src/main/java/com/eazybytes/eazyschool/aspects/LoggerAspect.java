package com.eazybytes.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.eazybytes.eazyschool..*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info( joinPoint.getSignature().toString()+ " method execution start");
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start,end).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method is " + timeElapsed);
        log.info( joinPoint.getSignature().toString()+ " method execution end");
        return result;
    }

    @AfterThrowing(value = "execution(* com.eazybytes.eazyschool..*.*(..))" , throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) throws Throwable {
        log.info(joinPoint.getSignature().toString()+" An exception occurred due to " + ex.getMessage());
    }
}
