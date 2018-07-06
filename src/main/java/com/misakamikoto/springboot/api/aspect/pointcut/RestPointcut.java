package com.misakamikoto.springboot.api.aspect.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class RestPointcut {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void get() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void post() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void put() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delete() {}
}
