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
    public static Map<String, CachEntity> cachedMap = new ConcurrentHashMap<String, CachEntity>();

    public static Map<String, CachEntity> getCachedMap() {
        return cachedMap;
    }

    public static void setCachedMap(Map<String, CachEntity> cachedMap) {
        Processor.cachedMap = cachedMap;
    }
}
