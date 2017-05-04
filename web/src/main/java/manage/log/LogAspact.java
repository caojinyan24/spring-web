package manage.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 日志统计
 * Created by jinyancao on 10/24/16.
 */
@Aspect
@Component
public class LogAspact {
    private static final Logger logger = LoggerFactory.getLogger(LogAspact.class);
    Profiler profile;

    @PostConstruct()
    public void setUp() {
        profile = new Profiler("statics");
        profile.registerWith(ProfilerRegistry.getThreadContextInstance());//统计当前线程内的调用情况，线程结束后打印
    }



//    @Before("execution(* manage.*.*.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] object = joinPoint.getArgs();
//        logger.info("class:{}#method:{} begin#param:{}", className, methodName, object);
//    }
//
//    @AfterReturning(returning = "result", pointcut = "execution(* manage.*.*.*(..))")
//    public void logAfter(JoinPoint joinPoint, Object result) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("class:{}#method:{} end#response:{}", className, methodName, result);
//    }

    @Around("execution(* manage.*.*.*(..))")//对controller层无法做环切？？
    public Object collectRunStatics(ProceedingJoinPoint pjp) {
        logger.info("{} invoke begin:{}", pjp.toLongString(), pjp.getArgs());
        System.out.println("test"+logger);
        profile.start(pjp.toShortString());
        Object result = null;
        try {
            result = pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        profile.stop().print();
        logger.info("{} invoke end:{}", pjp.toLongString(), result);
        return result;
    }
}
