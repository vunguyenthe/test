package com.trade.atoc.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;

import com.trade.atoc.activemq.config.SimpleCommunicationSystem;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.OrderClose;
import com.trade.atoc.message.OrderDelete;
import com.trade.atoc.message.OrderModify;
import com.trade.atoc.message.OrderSend;
import com.trade.atoc.processor.TASMessageProcessor;

public class InitService {
	private SimpleCommunicationSystem communicationSystem = null;
	static {
        JSONParser.registerType(OrderSend.class);
		JSONParser.registerType(OrderDelete.class);
		JSONParser.registerType(OrderClose.class);
		JSONParser.registerType(OrderModify.class);
	}
	@PostConstruct
    public void init(){
		try {
	        System.out.println("MyService init method called");
			TASMessageProcessor R1 = new TASMessageProcessor("TASMessageProcessor");
			communicationSystem = TASMessageProcessor.communicationSystem;
			R1.start();
			System.out.println("Init Tas Authentication Service is ok");
		}
		catch (JMSException e) {
			e.printStackTrace();
		}		
    }
    public SimpleCommunicationSystem getInstance()
    {
    	return communicationSystem;
    }
    public InitService(){
        System.out.println("MyService no-args constructor called");
    }
     
    @PreDestroy
    public void destory(){
        System.out.println("MyService destroy method called");
    }
}
