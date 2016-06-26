package com.trade.atoc.activemq.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class QueueMessageSender {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {

		this.jmsTemplate = jmsTemplate;
	}

	public void send(final Object Object) {

		jmsTemplate.convertAndSend(Object);
	}

	public void sendMessages(final String msg) throws JMSException {

		jmsTemplate.send(new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {

				TextMessage message = session.createTextMessage(msg);
				return message;
			}
		});
	}
}