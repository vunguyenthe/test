package com.trade.atoc.message;

import com.trade.atoc.enums.OrderSelectSource;

public class OrderSelect {
	Integer index; // index or order ticket
	Integer select; // flag
	OrderSelectSource pool = OrderSelectSource.MODE_TRADES; // mode
}
