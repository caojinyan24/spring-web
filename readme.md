运行:
1. 启动redis
2. 启动kafka
3. 创建主题:./kafka-topics.sh --create  --zookeeper 127.0.0.1:2181  --replication-factor 1 --partitions 1 --topic profileMsg
4. 启动kafka consumer：kafka.service.impl.consumerService.main