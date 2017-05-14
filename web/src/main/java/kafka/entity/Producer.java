package kafka.entity;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * kafkaProducer
 * Created by jinyan on 5/4/17.
 */
public class Producer extends Thread {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final KafkaProducer<String, String> producer;
    private String topic;
    private final String message;
    private static final Properties property = new Properties();

    static {
        try {
            property.load(Resources.getResource("producer.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        producer = new KafkaProducer<String, String>(property);

    }

    public Producer(String message, Boolean isAsycn) {
        this.message = message;
        this.topic = property.getProperty("topic");

    }

    //同步
    public void run() {
        logger.debug("send message:{},{}", topic, message);
        producer.send(new ProducerRecord<String, String>(topic, message));
        logger.info("send message end:{}", topic + "-" + message);
    }
}
