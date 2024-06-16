package com.sparta.newsfeedapp.aop;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j      // Log 객체 생성
@Aspect     // AOP에서 사용되는 어노테이션, 이 클래스가 관점임을 나타냄. 부가적인 기능을 모듈화하여 적용.
@Component  // spring Bean에 등록
public class ApiRequestAop {

    @Pointcut("execution(* com.sparta.newsfeedapp.controller..*(..))")
    private void logController() {}

    @Before("logController()")
    public void requestLog() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("Request URL: {}, HTTP Method: {}", request.getRequestURL(), request.getMethod());
        }
    }
}
