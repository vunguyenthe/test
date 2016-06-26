package com.trade.atoc.model;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.message.OrderDelete;

public class OrderDeleteTest extends BaseMessageTest {

	private static final UUID messageId = UUID.randomUUID();
	private static final Integer ticket = 123456789;
	private static final Color color = Color.BLUE;
	private static final String deviceId = "vunguyen";

	public OrderDeleteTest() {
		super(createMessage());
	}

	private static OrderDelete createMessage() {
		OrderDelete orderDelete = new OrderDelete();
		orderDelete.init(deviceId, messageId, ticket, color);
		return orderDelete;
	}
}
