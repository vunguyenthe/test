package com.trade.atoc.util;

import java.util.UUID;

import org.junit.Test;

import com.trade.atoc.enums.Color;
import com.trade.atoc.enums.OrderType;
import com.trade.atoc.enums.Symbol;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.OrderSend;

public class PushNotificationHelperTest {
	private static final UUID messageId = UUID.randomUUID();
	private static final String symbol = "EURUSD";
	private static final Integer cmd = 0;
	private static final Double volume = 0.1;
	private static final Double price = 100.000;
	private static final Integer slippage = 3;
	private static final Double stoploss = 100.000;
	private static final Double takeprofit = 120.000;
	private static final String deviceAlias = "vunguyen";
	
	@Test
	public void testRequestPushNotification() {
		OrderSend orderSend = new OrderSend();
		orderSend.init(deviceAlias, messageId, symbol, cmd, volume, price,
				slippage, stoploss, takeprofit, 1234, 0L, 0);
		JSONParser.registerType(OrderSend.class);
		String json = JSONParser.toJson(orderSend);
		PushNotificationHelper.requestPushNotificationWithAlias(json);
	}
}