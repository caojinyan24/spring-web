package manage.processor;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jinyancao on 2/6/17.
 */
@Component
public class Processor {
    /**
     * 持久层查询结果保存容器
     */
    public static Map<String, Object> cachedMap = new ConcurrentHashMap<String, Object>();

    public static Map<String, Object> getCachedMap() {
        return cachedMap;
    }

    public static void setCachedMap(Map<String, Object> cachedMap) {
        Processor.cachedMap = cachedMap;
    }
}