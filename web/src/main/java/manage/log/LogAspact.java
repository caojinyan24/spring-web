package manage.log;

import manage.anotation.Cach;
import manage.processor.Processor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by jinyancao on 10/24/16.
 */
@Aspect
@Component
public class LogAspact {
    private static final Logger logger = LoggerFactory.getLogger(LogAspact.class);

    @Before("execution(* manage.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] object = joinPoint.getArgs();
        logger.info("class:{}#method:{} begin#param:{}", className, methodName, object);
    }

    @AfterReturning(returning = "result", pointcut = "execution(* manage.*.*.*(..))")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("class:{}#method:{} end#response:{}", className, methodName, result);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Cach.class)) {
            String key = method.getName() + Arrays.hashCode(joinPoint.getArgs());
            Object value = Processor.getCachedMap().get(key);
            if (value == null) {
                Processor.getCachedMap().put(key, result);
            }
        }
    }
}
