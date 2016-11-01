package manage.service;

/**
 * redis存储服务类
 * Created by jinyancao on 11/1/16.
 */
public interface RedisService {
    void save(String key, String value);

    String get(String key);
}
