package manage.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jinyancao on 2/6/17.
 */
public class Processor {
    /**
     * 持久层查询结果保存容器
     */
    public static Map<String, CachEntity> cachedMap = new ConcurrentHashMap<String, CachEntity>();

    public static Map<String, CachEntity> getCachedMap() {
        removeInvalidCach();
        return cachedMap;

    }

    public static void setCachedMap(Map<String, CachEntity> cachedMap) {
        Processor.cachedMap = cachedMap;
    }

    private static void removeInvalidCach() {
        for (Map.Entry<String, CachEntity> iterator : cachedMap.entrySet()) {
            if (iterator.getValue().isInvalid()) {
                cachedMap.entrySet().remove(iterator);
            }

        }
    }
}
