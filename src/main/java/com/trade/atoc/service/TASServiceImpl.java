package com.trade.atoc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trade.atoc.activemq.config.SimpleCommunicationSystem;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.json.adapter.AdapterJsonBuilder;
import com.trade.atoc.message.base.BaseMessage;

@Service("tasService")
@Transactional
public class TASServiceImpl implements TASService {

	@Override
	public void fowardMessageToQueue(
			final SimpleCommunicationSystem communicationSystem,
			BaseMessage message) {
		String json ="";
		try {
		JSONParser.registerType(message.getClass());
		json = JSONParser.toJson(message);
		communicationSystem.sendMessage(json);
		System.out.println("json: " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
