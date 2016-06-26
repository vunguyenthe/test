package com.trade.atoc.controller;

import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.util.UriComponentsBuilder;

import com.trade.atoc.activemq.config.SimpleCommunicationSystem;
import com.trade.atoc.enums.MessageStatus;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.OrderClose;
import com.trade.atoc.message.OrderDelete;
import com.trade.atoc.message.OrderModify;
import com.trade.atoc.message.OrderSend;
import com.trade.atoc.processor.TASMessageProcessor;
import com.trade.atoc.service.TASService;
import com.trade.atoc.system.config.SystemConfiguration;

@RestController
public class TASController implements ServletConfigAware {

	@Autowired
	private ServletContext mServletContext;
	@Autowired
	TASService tasService; // Service which will do all data
	private static SimpleCommunicationSystem communicationSystem = null;
	private Map<System, SimpleCommunicationSystem> atocSystem;

	static 
	{
		SystemConfiguration.loadPropertieseConfiguration();
		JSONParser.registerType(OrderSend.class);
		JSONParser.registerType(OrderDelete.class);
		JSONParser.registerType(OrderClose.class);
		JSONParser.registerType(OrderModify.class);
		TASMessageProcessor R1;
		try {
			R1 = new TASMessageProcessor("TASMessageProcessor");
			communicationSystem = TASMessageProcessor.communicationSystem;
			R1.start();			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	public void setServletConfig(ServletConfig servletConfig) {
		mServletContext = servletConfig.getServletContext();
	}

	@RequestMapping(value = "/orderSendApproved", method = RequestMethod.POST)
	public OrderSend orderSendApproved(@RequestBody OrderSend obj,
			UriComponentsBuilder ucBuilder) {
		obj.setMessage_status(1);
		System.out.print(obj.toString());
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}
	@RequestMapping(value = "/orderSendCancelled", method = RequestMethod.POST)
	public OrderSend orderSendCancelled(@RequestBody OrderSend obj,
			UriComponentsBuilder ucBuilder) {
		obj.setMessage_status(2);
		System.out.print(obj.toString());
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}
	
	@RequestMapping(value = "/orderSend", method = RequestMethod.POST)
	public OrderSend orderSend(@RequestBody OrderSend obj,
			UriComponentsBuilder ucBuilder) {
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}

	@RequestMapping(value = "/orderClose", method = RequestMethod.POST)
	public OrderClose orderSend(@RequestBody OrderClose obj,
			UriComponentsBuilder ucBuilder) {
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}

	@RequestMapping(value = "/orderDelete", method = RequestMethod.POST)
	public OrderDelete orderDelete(@RequestBody OrderDelete obj,
			UriComponentsBuilder ucBuilder) {
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}

	@RequestMapping(value = "/orderModify", method = RequestMethod.POST)
	public OrderModify orderSend(@RequestBody OrderModify obj,
			UriComponentsBuilder ucBuilder) {
		tasService.fowardMessageToQueue(communicationSystem, obj);
		return obj;

	}
}
