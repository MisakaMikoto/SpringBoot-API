package com.misakamikoto.springboot.api.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingControllerException {
    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @AfterThrowing(pointcut = "com.misakamikoto.springboot.api.aspect.pointcut.RestExceptionPointcut.exception()", throwing = "exception")
    public void afterException(Exception exception) {
        logger.info("==================== REST API CALL EXCEPTION ====================");
        logger.info("Executing Exception : " + exception);
    }
}
