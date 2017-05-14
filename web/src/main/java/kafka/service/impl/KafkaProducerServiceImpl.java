package kafka.service.impl;

import kafka.entity.MessageVo;
import kafka.entity.Producer;
import kafka.service.KafkaProducerService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息发送
 * Created by jinyan on 5/4/17.
 */
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void sendMessage(MessageVo messageVo) {
        Producer producer = new Producer(messageVo.getMessage(), Boolean.TRUE);
        executorService.execute(producer);
    }


}
