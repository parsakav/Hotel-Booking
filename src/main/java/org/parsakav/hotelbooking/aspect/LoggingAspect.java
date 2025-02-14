package org.parsakav.hotelbooking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.parsakav.hotelbooking.service.*.*(..)) || execution(* org.parsakav.hotelbooking.controller.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Executing method: {}.{} with arguments: {}", className, methodName, methodArgs);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("Exception in method {}.{}: {}", className, methodName, e.getMessage());
            throw e;
        }

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("Method {}.{} executed (Duration: {} ms) - Output: {}", className, methodName, executionTime, result);

        return result;

    }
}