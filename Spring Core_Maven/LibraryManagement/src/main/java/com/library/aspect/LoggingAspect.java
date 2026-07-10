package com.library.aspect;


import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class LoggingAspect {
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.currentTimeMillis();
        Object result=joinPoint.proceed();
        long end=System.currentTimeMillis();
        System.out.print(joinPoint.getSignature().getName()+" Executed in: "+(end-start)+"ms");
        return result;
    }
}
