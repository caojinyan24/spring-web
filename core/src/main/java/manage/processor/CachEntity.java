package manage.processor;

/**
 * Created by jinyancao on 2/6/17.
 */
public class CachEntity {
    /**
     * 存入缓存中的值
     */
    Object value;
    /**
     * 存入时间
     */
    Long timestamp;

    /**
     * 过期时间
     */
    Long expiredTime;

    public boolean isInvalid() {
        return System.currentTimeMillis() < timestamp + expiredTime;
    }

    public CachEntity() {
    }

    public CachEntity(Object value, Long timestamp, Long expiredTime) {
        this.value = value;
        this.timestamp = timestamp;
        this.expiredTime = expiredTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CachEntity{");
        sb.append("value=").append(value);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", expiredTime=").append(expiredTime);
        sb.append('}');
        return sb.toString();
    }
}
