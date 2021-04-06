package com.klsoft.lms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SellerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Before(value = "execution(* com.klsoft.lms.controller.SellerRestController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Before method: {}", joinPoint.getSignature());
    }
}
