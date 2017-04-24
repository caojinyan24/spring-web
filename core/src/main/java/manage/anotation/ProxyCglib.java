package manage.anotation;

import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import manage.processor.CachEntity;
import manage.processor.Processor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 要么空指针要么ClassCastException-----改下方法调用即可(method.invoke(object, objects))
 * Created by jinyan on 4/22/17.
 */
public class ProxyCglib implements MethodInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ProxyCglib.class);

    Object object;

    public Object createInstance(Object object, Class clazz) {//为了使用spring实例化的类（保证类的依赖注入）
        this.object = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);//这里不能用代理过的类（从beanFactory中获取的实例是代理类）
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //o要求是EnhancerByCGLIB类型
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getDeclaredAnnotation(Cach.class) != null &&
                Processor.getCachedMap().get(method.getName()) != null) {
            logger.info("get data from cachMap");
            return Processor.getCachedMap().get(method.getName());
        } else {
            Object result = method.invoke(object, objects);
//            Object result = methodProxy.invokeSuper(o, objects);//第一个参数要求时不能传入spring的bean实例，会出现ClassCastException
            Processor.getCachedMap().put(method.getName(), new CachEntity(result, 1000L, 1000L));
            return result;
        }
    }

    /**
     * 出现的问题：
     * 再生成代理对象时，传入的CallBack要求传入当前代理类对象
     * 而supperClass要求传入被代理类
     * 再spring框架中涉及依赖注入的情况，需要再incercept中传入spring生成的bean实例，object将会是一个代理类的实例，导致设置callback类时出错，创建代理对象失败
     * Method threw 'java.lang.ClassCastException' exception. Cannot evaluate manage.service.impl.UserInfoServiceImpl$$EnhancerByCGLIB$$231ebdbf.toString()
     * invokeSuper中的o需要是被代理对象的一个实例（UserInfoServiceImpl），如果传入spring的bean实例，需要做class映射，如果spring使用了jdk代理，两个类是不能做映射的
     */
}
