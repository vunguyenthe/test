package com.trade.atoc.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.message.base.BaseMessage;

public class OrderModify extends BaseMessage {
	Integer ticket; // ticket
	Double price; // price
	Double stoploss; // stop loss
	Double takeprofit; // take profit
	String expiration; // expiration
	Color arrow_color; // color

	public void init(String deviceId, UUID messageId, Integer ticket,
			Double price, Double stoploss, Double takeprofit, Color arrow_color) {
		this.deviceAlias = deviceId;
		this.messageId = messageId;
		this.ticket = ticket;
		this.price = price;
		this.stoploss = stoploss;
		this.takeprofit = takeprofit;
		this.expiration = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		this.arrow_color = Color.BLUE;
		this.createdDate = System.currentTimeMillis();
	}

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStoploss() {
		return stoploss;
	}

	public void setStoploss(Double stoploss) {
		this.stoploss = stoploss;
	}

	public Double getTakeprofit() {
		return takeprofit;
	}

	public void setTakeprofit(Double takeprofit) {
		this.takeprofit = takeprofit;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public Color getArrow_color() {
		return arrow_color;
	}

	public void setArrow_color(Color arrow_color) {
		this.arrow_color = arrow_color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrow_color == null) ? 0 : arrow_color.hashCode());
		result = prime * result
				+ ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((stoploss == null) ? 0 : stoploss.hashCode());
		result = prime * result
				+ ((takeprofit == null) ? 0 : takeprofit.hashCode());
		result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderModify other = (OrderModify) obj;
		if (arrow_color != other.arrow_color)
			return false;
		if (expiration == null) {
			if (other.expiration != null)
				return false;
		} else if (!expiration.equals(other.expiration))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (stoploss == null) {
			if (other.stoploss != null)
				return false;
		} else if (!stoploss.equals(other.stoploss))
			return false;
		if (takeprofit == null) {
			if (other.takeprofit != null)
				return false;
		} else if (!takeprofit.equals(other.takeprofit))
			return false;
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderModify [ticket=" + ticket + ", price=" + price
				+ ", stoploss=" + stoploss + ", takeprofit=" + takeprofit
				+ ", expiration=" + expiration + ", arrow_color=" + arrow_color
				+ "]";
	}

}
