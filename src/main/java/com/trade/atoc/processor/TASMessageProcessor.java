package com.trade.atoc.processor;

import javax.jms.JMSException;

import com.trade.atoc.activemq.config.SimpleCommunicationSystem;
import com.trade.atoc.util.PushNotificationHelper;

public class TASMessageProcessor implements Runnable {
	private Thread thread;
	private String threadName;
	public static SimpleCommunicationSystem communicationSystem = null;
	public TASMessageProcessor(String name) throws JMSException {
		threadName = name;
		System.out.println("Creating " + threadName);
		init();
	}

	public void processMessage(String message) {
	 	 System.out.println("processMessage -> request notify the message to cell phone: " + message);
		 PushNotificationHelper.requestPushNotification(message);
	}

	public String init() throws JMSException {
		if (communicationSystem == null) {
			communicationSystem = new SimpleCommunicationSystem();
		}
		return "Trade Authentication Service Init";

	}

	public void run() {
		while (true) {
			String message = null;
			try {
				message = communicationSystem.getReceivedMessage();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			processMessage(message);
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
}
