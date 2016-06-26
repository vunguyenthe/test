package com.trade.atoc.model;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.enums.OrderType;
import com.trade.atoc.enums.Symbol;
import com.trade.atoc.message.OrderClose;

public class OrderCloseTest extends BaseMessageTest {
	private static final Integer ticket = 123456789;
	private static final UUID messageId = UUID.randomUUID();
	private static final Symbol symbol = Symbol.USDCAD;
	private static final OrderType cmd = OrderType.OP_BUY;
	private static final Double volume = 0.1;
	private static final Double price = 100.000;
	private static final Integer slippage = 3;
	private static final Double lots = 1.00;
	private static final Double pric = 100.000;
	private static final Double takeprofit = 120.000;
	private static final String clienId = "vunguyen";

	public OrderCloseTest() {
		super(createMessage());
	}

	private static OrderClose createMessage() {
		OrderClose orderClose = new OrderClose();
		orderClose.init(clienId, messageId, ticket, lots, pric, slippage,
				Color.RED);
		return orderClose;
	}
}
