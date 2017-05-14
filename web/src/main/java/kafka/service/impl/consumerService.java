package kafka.service.impl;

import kafka.entity.Consumer;

/**
 * Created by jinyan on 5/14/17.
 */
public class consumerService {
    public static void main(String[] args) {
        new Consumer("profileMsg").start();
    }
}
