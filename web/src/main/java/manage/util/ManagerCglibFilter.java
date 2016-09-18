package manage.util;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by jinyan.cao on 2016/4/24.
 */
public class ManagerCglibFilter implements CallbackFilter {
    public int accept(Method method) {
        return 0;
    }
}
