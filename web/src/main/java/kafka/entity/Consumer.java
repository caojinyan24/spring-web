package kafka.entity;

import com.google.common.io.Resources;
import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * kafka消费者
 * 接收Profile日志信息并发送消息
 * Created by jinyan on 5/4/17.
 */
public class Consumer extends ShutdownableThread {
    private static final Properties property = new Properties();
    public static AtomicBoolean isStarted = new AtomicBoolean(Boolean.FALSE);
    private static PrintWriter pw;

    static {
        try {

            pw = new PrintWriter(new File("web/src/main/log/logfile.log"));
            property.load(Resources.getResource("consumer.properties").openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private final KafkaConsumer<String, String> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("kafkaConsumer", false);
        consumer = new KafkaConsumer<String, String>(property);
        this.topic = topic;
    }


    @Override
    public void doWork() {
        isStarted.compareAndSet(Boolean.TRUE, Boolean.FALSE);
        if (isStarted.get()) {
            return;
        }
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (ConsumerRecord<String, String> record : records) {
            process(record);
        }
    }

    private void process(ConsumerRecord<String, String> record) {
        pw.append(record.key()).append("#########################").append(record.value()).append("###########").append(new Date().toString()).append("\n");
        pw.flush();
    }
}
