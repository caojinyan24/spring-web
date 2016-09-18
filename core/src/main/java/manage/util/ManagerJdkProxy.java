package manage.util;


import com.google.common.base.Splitter;
import manage.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * jdk动态代理
 * 通过配置文件配置不同的权限，这个类用来做权限控制
 * Created by jinyan.cao on 2016/4/18.
 */
@Component
public class ManagerJdkProxy implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(ManagerJdkProxy.class);
    @Resource
    UserManageService UserManageService;

    @Value("${query}")
    String canQuery;
    @Value("${update}")
    String canUpdate;

    @PostConstruct
    public void setUp() {
        canQueryList = Splitter.on(",").trimResults().splitToList(canQuery);
        canUpdateList = Splitter.on(",").trimResults().splitToList(canUpdate);
    }

    private List<String> canQueryList;
    private List<String> canUpdateList;

    /**
     * @return
     */
    public Object createInstance(String managerName) {
        UserManageService.setUserName(managerName);
        ClassLoader classLoader = UserManageService.getClass().getClassLoader();
        //UserManage.class.getInterfaces()返回的是null，所以这里要么用impl，要么用实例对象获得接口方法数组
        //Class<?>[] methods = UserManageImpl.class.getInterfaces();
        Class<?>[] methods = UserManageService.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, methods, this);


    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (canQuery()) {
            return UserManageService.queryUserInfo();
        } else {
            logger.error("{} deny query", UserManageService.getUserName());
            return null;
        }
    }

    public boolean canQuery() {
        return canQueryList.contains(UserManageService.getUserName());
    }

    public boolean canUpdate() {
        return canUpdateList.contains(UserManageService.getUserName());

    }

}
