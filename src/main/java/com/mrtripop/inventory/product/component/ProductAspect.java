package com.mrtripop.inventory.product.component;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProductAspect {

  @Pointcut("execution(public * com.mrtripop.inventory.product.*.*.*(..))")
  private void publicMethodsEntireApplication() {}

  @Around(value = "publicMethodsEntireApplication()")
  public Object handleLogMessageEntireApplication(ProceedingJoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String methodName = joinPoint.getSignature().getName();
    log.debug(">> {}.{}({})", className, methodName, Arrays.toString(args));
    Object result = joinPoint.proceed();
    log.debug("<< {}.{}: {}", className, methodName, result);
    return result;
  }
}
