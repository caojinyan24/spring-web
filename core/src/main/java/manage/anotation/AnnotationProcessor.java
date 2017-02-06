package manage.anotation;

import manage.processor.Processor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by jinyancao on 2/6/17.
 */
@Aspect
@Component
public class AnnotationProcessor {
    private static Logger logger = LoggerFactory.getLogger(AnnotationProcessor.class);

    @AfterReturning(returning = "result", pointcut = "execution(* manage.dao.*(..))")
    public void processCachableAnnotation(JoinPoint joinPoint, Object result) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        logger.debug("processCachableAnnotation:{}", joinPoint, result);
//        if (method.isAnnotationPresent(Cach.class)) {
//            String key = method.getName() + Arrays.hashCode(joinPoint.getArgs());
//            Object value = Processor.getCachedMap().get(key);
//            if (value == null) {
//                Processor.getCachedMap().put(key, result);
//            }
//        }
    }
}
