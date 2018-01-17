package manage.cachProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存注解，使用hashmap保存方法返回值
 * Created by jinyancao on 2/6/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cach {
    /**
     * 过期时间
     */
    long expiredTime() default 0L;
}
