package com.trade.atoc.message;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.message.base.BaseMessage;

public class OrderDelete extends BaseMessage {

	private Integer ticket; // ticket
	private Color arrow_color; // color

	public Integer getTicket() {
		return ticket;
	}

	/**
	 * @param messageId
	 * @param ticket
	 * @param arrow_color
	 */
	public void init(String deviceId, UUID messageId, Integer ticket,
			Color arrow_color) {
		this.deviceAlias = deviceId;
		this.messageId = messageId;
		this.ticket = ticket;
		this.arrow_color = arrow_color;
		this.createdDate = System.currentTimeMillis();
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
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
		int result = super.hashCode();
		result = prime * result
				+ ((arrow_color == null) ? 0 : arrow_color.hashCode());
		result = prime * result + ((ticket == null) ? 0 : ticket.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDelete other = (OrderDelete) obj;
		if (arrow_color != other.arrow_color)
			return false;
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		return true;
	}

}
