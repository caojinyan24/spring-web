package kafka.entity;

/**
 * kafka消息
 * Created by jinyan on 5/9/17.
 */
public class MessageVo {
    String topic;
    String message;

    public MessageVo() {
    }

    public MessageVo(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
