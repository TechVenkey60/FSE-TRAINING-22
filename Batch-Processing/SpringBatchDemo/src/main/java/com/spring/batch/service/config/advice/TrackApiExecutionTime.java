package com.spring.batch.service.config.advice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TrackApiExecutionTime {

    @SneakyThrows
    @Around("@annotation(com.spring.batch.service.config.advice.annotation.TrackExecutionTime)")
    public Object trackMethodExecutionTime(ProceedingJoinPoint joinPoint){
        long startTime = System.currentTimeMillis();
        var  obj = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.debug("Method "+joinPoint.getSignature()+" has been taken "+(endTime-startTime)+" milli-Seconds");
        return obj;
    }
}
