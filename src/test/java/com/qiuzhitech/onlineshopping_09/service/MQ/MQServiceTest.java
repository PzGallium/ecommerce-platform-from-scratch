package com.qiuzhitech.onlineshopping_09.service.MQ;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MQServiceTest {
    @Resource
    private MQService mqService;
    @Test
    void sendMessage() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        mqService.sendMessage("topic1", "Hello test1" + new Date());
    }

    @Test
    void sendMessageBatch() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        for (int i = 0; i < 10; i++) {
            mqService.sendMessageFIFO("topic1", "Hello test" + i + " " + new Date());
        }
    }
}