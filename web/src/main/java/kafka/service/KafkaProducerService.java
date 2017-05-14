package kafka.service;

import kafka.entity.MessageVo;

/**
 * Created by jinyan on 5/4/17.
 */
public interface KafkaProducerService {
    public void sendMessage(MessageVo messageVo);

}
