package com.flair.flair.core.logging;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

  private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

  @Around("execution(* com.flair.flair.service.impl..*(..))")
  public Object logServiceMethods(ProceedingJoinPoint point) throws Throwable {
    long start = System.currentTimeMillis();
    String methodName = point.getSignature().toShortString();
    String args = Arrays.toString(point.getArgs());

    LOGGER.info("Called {} with arguments {}", methodName, args);

    try {
      Object result = point.proceed();
      long timeTaken = System.currentTimeMillis() - start;
      LOGGER.info("✅ Finished method: {} in {} ms, result: {}", methodName, timeTaken, result);
      return result;
    } catch (Throwable e) {
      LOGGER.error(
          "Error in method: {} with arguments: {}. Exception: {}",
          methodName,
          args,
          e.getMessage(),
          e);
      throw e;
    }
  }
}
