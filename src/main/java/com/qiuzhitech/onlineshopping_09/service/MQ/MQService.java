package com.qiuzhitech.onlineshopping_09.service.MQ;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MQService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message message1 = new Message(topic, message.getBytes());
        rocketMQTemplate.getProducer().send(message1);
    }

    public void sendMessageFIFO(String topic, String message) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message message1 = new Message(topic, message.getBytes());
        rocketMQTemplate.getProducer().send(message1, (mqs, msg, arg) -> mqs.get(0), null);
    }
}
