package org.parsakav.hotelbooking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NullCheckAspect {

    @Before("""
execution(* org.parsakav.hotelbooking.repository.*.*(..))
           || execution(* org.parsakav.hotelbooking.service.*.*(..))
            """)
    public void checkForNull(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg == null) {
                throw new IllegalArgumentException("Argument cannot be null.");
            }
        }
    }
}