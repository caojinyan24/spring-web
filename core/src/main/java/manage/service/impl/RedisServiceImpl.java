package manage.service.impl;

import manage.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * redis存储服务类
 * Created by jinyancao on 11/1/16.
 */
@Service
public class RedisServiceImpl implements RedisService {
    public ShardedJedis jedis;
    @Resource
    private ShardedJedisPool shardedJedisPool;

    @PostConstruct
    public void setUp() {
        jedis = shardedJedisPool.getResource();
    }

    public void save(String key, String value) {
        jedis.set(key, value);
    }

    public String get(String key) {
        return jedis.get(key);
    }
}
