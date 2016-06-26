package com.trade.atoc.message;

import java.util.UUID;

import com.trade.atoc.enums.Color;
import com.trade.atoc.enums.MessageStatus;
import com.trade.atoc.enums.OrderType;
import com.trade.atoc.enums.Symbol;
import com.trade.atoc.message.base.BaseMessage;

public class OrderSend extends BaseMessage {
	private String symbol; // symbol
	private Integer cmd; // operation
	private Double volume; // volume
	private Double price; // price
	private Integer slippage; // slippage
	private Double stoploss; // stop loss
	private Double takeprofit; // take profit
	private String comment; // comment
	private Integer magic = 0; // magic number
	private Long expiration; // pending order expiration
	private Integer arrow_color =0; // color
	private Integer message_status = 0;

	public Integer getMessage_status() {
		return message_status;
	}

	public void setMessage_status(Integer message_status) {
		this.message_status = message_status;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSlippage() {
		return slippage;
	}

	public void setSlippage(Integer slippage) {
		this.slippage = slippage;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getMagic() {
		return magic;
	}

	public void setMagic(Integer magic) {
		this.magic = magic;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public int getArrow_color() {
		return arrow_color;
	}

	public void setArrow_color(int arrow_color) {
		this.arrow_color = arrow_color;
	}
	/**
	 * @param clienId
	 * @param symbol
	 * @param cmd
	 * @param volume
	 * @param price
	 * @param slippage
	 * @param stoploss
	 * @param takeprofit
	 * @param comment
	 * @param magic
	 * @param expiration
	 * @param arrow_color
	 */
	public void init(String deviceId, UUID messageId, String symbol,
			Integer cmd, Double volume, Double price, Integer slippage,
			Double stoploss, Double takeprofit, Integer magic,
			Long expiration, int arrow_color) {
		this.deviceAlias = deviceId;
		this.messageId = messageId;
		this.symbol = symbol;
		this.cmd = cmd;
		this.volume = volume;
		this.price = price;
		this.slippage = slippage;
		this.stoploss = stoploss;
		this.takeprofit = takeprofit;
		this.comment = "test only";
		this.magic = magic;
		this.expiration = expiration;
		this.arrow_color = arrow_color;
		this.createdDate = System.currentTimeMillis();
	}
}
