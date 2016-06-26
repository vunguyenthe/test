package com.trade.atoc.message;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.message.base.BaseMessage;

public class OrderClose extends BaseMessage {

	private Integer ticket; // ticket
	private Double lots; // volume
	private Double pric; // close price
	private Integer slippage; // slippage
	private Color arrow_color; // color

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public Double getLots() {
		return lots;
	}

	public void setLots(Double lots) {
		this.lots = lots;
	}

	public Double getPric() {
		return pric;
	}

	public void setPric(Double pric) {
		this.pric = pric;
	}

	public Integer getSlippage() {
		return slippage;
	}

	public void setSlippage(Integer slippage) {
		this.slippage = slippage;
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
		result = prime * result + ((lots == null) ? 0 : lots.hashCode());
		result = prime * result + ((pric == null) ? 0 : pric.hashCode());
		result = prime * result
				+ ((slippage == null) ? 0 : slippage.hashCode());
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
		OrderClose other = (OrderClose) obj;
		if (arrow_color != other.arrow_color)
			return false;
		if (lots == null) {
			if (other.lots != null)
				return false;
		} else if (!lots.equals(other.lots))
			return false;
		if (pric == null) {
			if (other.pric != null)
				return false;
		} else if (!pric.equals(other.pric))
			return false;
		if (slippage == null) {
			if (other.slippage != null)
				return false;
		} else if (!slippage.equals(other.slippage))
			return false;
		if (ticket == null) {
			if (other.ticket != null)
				return false;
		} else if (!ticket.equals(other.ticket))
			return false;
		return true;
	}

	/**
	 * @param ticket
	 * @param lots
	 * @param pric
	 * @param slippage
	 * @param arrow_color
	 */
	public void init(String deviceId, UUID messageId, Integer ticket,
			Double lots, Double pric, Integer slippage, Color arrow_color) {
		this.deviceAlias = deviceId;
		this.messageId = messageId;
		this.ticket = ticket;
		this.lots = lots;
		this.pric = pric;
		this.slippage = slippage;
		this.arrow_color = arrow_color;
		this.createdDate = System.currentTimeMillis();
	}
}
