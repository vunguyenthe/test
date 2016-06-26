package com.trade.atoc.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.BlockingQueue;

public class QueueMessageReceiver implements MessageListener {

    private BlockingQueue<String> blockingQueue;

    public void onMessage(Message message) {
        System.out.println("onMessage()");
        if (message instanceof TextMessage) {
            try {
                String mesg = ((TextMessage) message).getText();
                blockingQueue.offer(mesg);
                System.out.println(mesg);
            } catch (JMSException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {

        this.blockingQueue = blockingQueue;
    }

}
