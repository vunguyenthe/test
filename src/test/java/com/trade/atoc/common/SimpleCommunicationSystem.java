package com.trade.atoc.common;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.trade.atoc.system.config.SystemConfiguration;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleCommunicationSystem {
    private static final Logger logger = LoggerFactory
            .getLogger(SimpleCommunicationSystem.class);

    private QueueMessageReceiver listener = null;
    private QueueMessageSender publisher = null;
	String user = "admin";
	String password = "admin";
	private DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
	private ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user,password, SystemConfiguration.broker_url);

    private String messageSend;

    private static final int SIZE_QUEUE = 10000;
    private BlockingQueue<String> eventQueue = new LinkedBlockingQueue<String>(
            SIZE_QUEUE);
    JmsTemplate jmsTemplate = new JmsTemplate();

    public SimpleCommunicationSystem() {

        eventQueue = new LinkedBlockingQueue<String>(SIZE_QUEUE);
        init();
        listener.setBlockingQueue(eventQueue);

    }

    public void registerMessageListener(String destinationName) {
        if (listener == null) {
            listener = new QueueMessageReceiver();
            container.setConnectionFactory(connectionFactory);
            container.setDestinationName(destinationName);
            container.setMessageListener(listener);
            container.afterPropertiesSet();
            container.start();
            System.out.println("registerMessageListener is ok -> "
                    + destinationName);
        }

    }

    public void registerPublisher(String destinationName) {
        if (publisher == null) {
            publisher = new QueueMessageSender();
            DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
            defaultMessageListenerContainer
                    .setConnectionFactory(connectionFactory);
            Destination destination = new ActiveMQQueue(destinationName);
            defaultMessageListenerContainer.setDestination(destination);
            jmsTemplate.setConnectionFactory(connectionFactory);
            jmsTemplate.setDefaultDestination(destination);
            publisher.setJmsTemplate(jmsTemplate);
            System.out.println("registerPublisher is ok -> "
                    + destinationName);
        }
    }

    public void init() {
        registerMessageListener(SystemConfiguration.tas_atoc);
        registerPublisher(SystemConfiguration.atoc_tas);
    }

    public String getReceivedMessage() {

        String ret = null;
        try {
            ret = eventQueue.take();
        } catch (InterruptedException e) {
            logger.error("Message receive failed : " + e.getMessage(), e);
        }
        return ret;
    }

    public void sendMessage(String message) {

        this.getPublisher().send(message);
    }

    public void setMessageSend(String messageSend) {

        this.messageSend = messageSend;
    }

    public QueueMessageSender getPublisher() {

        return publisher;
    }

    public int getSizeEventQueue() {

        return this.eventQueue.size();
    }
}
