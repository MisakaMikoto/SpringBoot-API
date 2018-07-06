package com.misakamikoto.springboot.api.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class RestExceptionPointcut {
    @Pointcut("execution(* com.misakamikoto.springboot.api..*(..))")
    public void exception() {}
}
