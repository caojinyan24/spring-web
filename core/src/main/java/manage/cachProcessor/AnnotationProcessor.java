package manage.cachProcessor;

import manage.processor.CachEntity;
import manage.processor.Processor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 利用spring的aop实现的缓存注解
 * Created by jinyancao on 2/6/17.
 */
@Aspect
@Component
public class AnnotationProcessor {
    private static Logger logger = LoggerFactory.getLogger(AnnotationProcessor.class);

    //这种处理方式需要调用者去判断是否能从缓存中取到结果
    //怎么实现对方法做代理，由缓存的注解处理器来对方法进行调用？
    @AfterReturning(returning = "result", pointcut = "execution(* manage.dao.*(..))")
    public void processCachableAnnotation(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.debug("processCachableAnnotation:{}", joinPoint, result);
        if (method.isAnnotationPresent(Cach.class)) {
            String key = method.getName() + Arrays.hashCode(joinPoint.getArgs());
            Object value = Processor.getCachedMap().get(key);
            if (value == null) {
                Processor.getCachedMap().put(key, new CachEntity(result, 1000L, 1000L));
            }
        }
    }


}
