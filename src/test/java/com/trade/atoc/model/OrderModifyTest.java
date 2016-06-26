package com.trade.atoc.model;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.enums.OrderType;
import com.trade.atoc.enums.Symbol;
import com.trade.atoc.message.OrderModify;

public class OrderModifyTest extends BaseMessageTest {
	private static final Integer ticket = 123456789;
	private static final UUID messageId = UUID.randomUUID();
	private static final Symbol symbol = Symbol.USDCAD;
	private static final OrderType cmd = OrderType.OP_BUY;
	private static final Double volume = 0.1;
	private static final Double price = 100.000;
	private static final Integer slippage = 3;
	private static final Double stoploss = 100.000;
	private static final Double takeprofit = 120.000;
	private static final String deviceId = UUID.randomUUID().toString();

	public OrderModifyTest() {
		super(createMessage());
	}

	public static OrderModify createMessage() {
		OrderModify orderModify = new OrderModify();
		orderModify.init(deviceId, messageId, ticket, price, stoploss,
				takeprofit, Color.BLUE);
		return orderModify;
	}
}
