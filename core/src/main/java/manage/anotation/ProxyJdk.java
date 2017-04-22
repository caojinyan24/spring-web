package manage.anotation;

import manage.dao.UserInfoMapper;
import manage.processor.CachEntity;
import manage.processor.Processor;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.util.StringSwitcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jinyan on 4/22/17.
 */
public class ProxyJdk implements InvocationHandler {
    private static Logger logger = LoggerFactory.getLogger(ProxyJdk.class);

    Object object;

    public Object createInstance(Object object) {
        this.object = object;//object在调用newProxyInstance之前若未赋值，会导致创建实例时出现空指针异常
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] proxyInterface = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, proxyInterface, this);
    }

    //proxy是生成的代理对象，是一个Proxy实例，其中的成员变量h是对应的Handler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaredAnnotationsByType(Cach.class) != null
                && Processor.getCachedMap().get(method.getName()) != null) {
            logger.info("get data from cachMap");
            return Processor.getCachedMap().get(method.getName()).getValue();
        } else {
            Object result = method.invoke(object, args);
            Processor.getCachedMap().put(method.getName(), new CachEntity(result, 1000L, 1000L));
            return result;
        }
    }


}
