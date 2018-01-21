package com.dmurchkov.service.agency.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
@Slf4j
public class AgencyServiceAspect {

    @Before(value = "execution(* com.dmurchkov.service.agency.AgencyService.* (..))")
    public void before(JoinPoint joinPoint) {
        log.info("Method {} run with parameters: {}", joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* com.dmurchkov.service.agency.AgencyService.* (..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.info("Method {} returned value: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "execution(* com.dmurchkov.service.agency.AgencyService.* (..))", throwing = "error")
    public void afterException(JoinPoint joinPoint, Throwable error) {
        log.info("Method {} threw: {}", joinPoint.getSignature().getName(), error.toString());
    }
}