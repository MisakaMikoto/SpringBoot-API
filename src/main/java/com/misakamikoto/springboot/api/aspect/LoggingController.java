package com.misakamikoto.springboot.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingController {
    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Before("com.misakamikoto.springboot.api.aspect.pointcut.RestPointcut.get()")
    public void beforeGetMapping(JoinPoint joinPoint) {
        logger.info("==================== REST API CALL ====================");
        logger.info("Executing GET Type method : " + joinPoint.getSignature().getName());
    }

    @Before("com.misakamikoto.springboot.api.aspect.pointcut.RestPointcut.post()")
    public void beforePostMapping(JoinPoint joinPoint) {
        logger.info("==================== REST API CALL ====================");
        logger.info("Executing POST Type method : " + joinPoint.getSignature().getName());
    }

    @Before("com.misakamikoto.springboot.api.aspect.pointcut.RestPointcut.put()")
    public void beforePutMapping(JoinPoint joinPoint) {
        logger.info("==================== REST API CALL ====================");
        logger.info("Executing PUT Type method : " + joinPoint.getSignature().getName());
    }

    @Before("com.misakamikoto.springboot.api.aspect.pointcut.RestPointcut.delete()")
    public void beforeDeleteMapping(JoinPoint joinPoint) {
        logger.info("==================== REST API CALL ====================");
        logger.info("Executing DELETE Type method : " + joinPoint.getSignature().getName());
    }

    @Before("com.misakamikoto.springboot.api.aspect.pointcut.RestPointcut.view()")
    public void beforeViewMapping(JoinPoint joinPoint) {
        logger.info("==================== View Page CALL ====================");
        logger.info("Executing View Page method : " + joinPoint.getSignature().getName());
    }
}
